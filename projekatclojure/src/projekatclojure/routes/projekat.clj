(ns projekatclojure.routes.projekat
  (:require [compojure.core :refer :all]
            [selmer.parser :refer [render-file]]
            [projekatclojure.models.komunikacija :as db]
            [compojure.response :refer [render]]
            [buddy.auth :refer [authenticated?]]
            [liberator.core :refer [defresource]]
            [clojure.data.json :as json]
            [struct.core :as st]
            [clojure.java.io :as io]
            [liberator.representation :refer [ring-response as-response]]
            [clojure.set :refer [rename-keys]]
            [clojure.string :as str]
            [ring.util.response :refer [redirect]]))

(def file-config (clojure.edn/read-string (slurp "file-config.edn")))

(defn create-file-name [{:keys [fname content-type]}]
  (str (:short-img-location file-config) fname "." (last (str/split content-type #"/"))))

(defn get-picture-url [params]
  (if (contains? params :url)
    (:url params)
    (->(assoc (:file params) :fname (:name params))
       (create-file-name))))

(def projekat-schema
  {:tip [st/required st/string]
   :naziv [st/required st/string]
   :lokacija [st/required st/string]
   :opis [st/required st/string]
   :slika [st/required st/string]
   :godinaZavrsetka [st/required st/number]
   :investitorID [st/required st/number]})

(defn projekat-validation? [params]
  (st/valid? {:tip (:tip params)
              :naziv (:naziv params)
              :lokacija (:lokacija params)
              :opis (:opis params)
              :slika (:slika params)
              :godinaZavrsetka (:godinaZavrsetka params)
              :investitorID (:investitorID params)} projekat-schema))

(defn get-projekti-page [page session]
  (render-file page
               {:title "Projekti"
                :logged (:identity session)
                :projekti (db/get-projekti)}))

(defn authenticated-admin? [session]
  (and (authenticated? session)
       (="admin" (:rola (:identity session)))))

(defn upload-picture [{:keys [fname tempfile]}]
  (io/copy tempfile (io/file (:resources-folder file-config) fname)))

(defn projekti [session]  
   (cond
    (not (authenticated? session))
    (redirect "/login")
    (authenticated-admin? session)
    (render-file "views/projekat-search-admin.html" {:title "Prikaz projekata"
                                                     :logged (:identity session)
                                                     :projekti (db/get-projekti)})
    :else
    (get-projekti-page "views/projekti.html" session)))

(defn get-add-projekat-page [session &[message]]
  (if-not (authenticated? session)
    (redirect "/vlogin")
    (render-file "views/projekat-add.html" {:title "Dodaj projekat"
                                            :logged (:identity session)})))

(defn add-projekat [{:keys [params session]}]
    (println params)
    (projekat-validation? params)
    (let [file (get-picture-url params)]
    (if-not (contains? params :url)
      (->(assoc (:file params) :fname file))
      (upload-picture))
    (-> (dissoc (assoc params :slika file) :file :url)
        (db/add-projekat params)
        (:generated_key)))    
    (redirect "/vlasnikForma"))

(defn get-projekat-slika-from-db [params]
  (:slika (first (db/find-projekat (select-keys params [:projekatID])))))

(defn file-exists? [params]
  (.exists (clojure.java.io/as-file (str (:resources-folder file-config) (get-projekat-slika-from-db params)))))

(defn get-projekti [text]
  (if (or (nil? text)
          (= "" text))
    (db/get-projekti)
    (db/search-projekti text)))

(defn get-search-projekti [params session]
  (render-file "views/projekat-search-vl.html" {:title "Search projekat"
                                             :logged (:identity session)
                                             :projekti (get-projekti nil)}))

(defresource search-projekat [{:keys [params session]}]
  :allowed-methods [:post]
  :authenticated? (authenticated? session)
  :handle-created (json/write-str (get-projekti (:text params)))
  :available-media-types ["application/json"])

(defresource search-projekat [{:keys [params session]}]
  :allowed-methods [:get]
  :available-media-types ["text/html" "application/json"]
  :handle-ok #(let [media-type (get-in % [:representation :media-type])]
                (condp = media-type
                  "text/html" (get-search-projekti params session)
                  "application/json" (->(:text params)
                                        (get-projekti)
                                        (json/write-str)))))

(defn get-projekat-edit-page [page params session]
  (render-file page {:title "Projekat"
                     :logged (:identity session)
                     :projekat (first (db/find-projekat params))}))

(defn get-projekat [{:keys [params session]}]
   (if-not (authenticated? session)
    (redirect "/vlogin")
    (get-projekat-edit-page "views/edit-projekat.html" params session)))

(defresource update-projekat [{:keys [params session]}]
  :allowed-methods [:put]  
  :available-media-types ["application/json"]
  (println params)
  (db/update-projekat params))

(defresource delete-projekat [{:keys [params session]}]
  :allowed-methods [:delete]  
  :available-media-types ["application/json"])

(defroutes projekat-routes
  (GET "/projekti" request (projekti (:session request)))
  (GET "/addprojekat" request (get-add-projekat-page (:session request)))
  (POST "/addprojekat" request (add-projekat request))
  (GET "/pretragaprojekata" request (search-projekat request))
  (GET "/projekat/:projekatID" request (get-projekat request))
  (PUT "/projekat" request (update-projekat request))
  (DELETE "/projekat" request (delete-projekat request)))