(ns projekatclojure.routes.stan
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

(def stan-schema
  {:link [st/required st/string]
   :sajt [st/required st/string]
   :kvadratura [st/required st/number]
   :cenaEvri [st/required st/number]
   :lokacija [st/required st/string]
   :slobodan [st/required st/number]
	 :terasa [st/required st/number]
	 :garazaParking [st/required st/string]
   :slika [st/required st/string]
   :opis [st/required st/string]
   :prodavacID [st/required st/number]})

(defn stan-validation? [params]
  (st/valid? {:link (:link params)
              :sajt (:sajt params)
              :kvadratura (read-string (:kvadratura params))
              :cenaEvri (read-string (:cenaEvri params))
              :lokacija (:lokacija params)
              :slobodan (read-string (:slobodan params))
              :terasa (read-string (:terasa params))
              :garazaParking (:garazaParking params)
              :slika (:slika params)
              :opis (:opis params)
              :prodavacID (read-string (:prodavacID params))} stan-schema))

(defn get-stanovi-page [page session]
  (render-file page
               {:title "Stanovi"
                :logged (:identity session)
                :stanovi (db/get-stan)
                :favoriti (db/get-favorit)}))

(defn get-stan-slika-from-db [params]
  (:slika (first (db/find-stan (select-keys params [:stanID])))))

(defn file-exists? [params]
  (.exists (clojure.java.io/as-file (str (:resources-folder file-config) (get-stan-slika-from-db params)))))


(defn stanovi [session]
    (get-stanovi-page "views/stanovi.html" session))

(defn get-stanovi [text]
  (if (or (nil? text)
          (= "" text))
    (db/get-stan)
    (db/search-stan text)))

(defn get-stanovi-vl [text]
  (if (or (nil? text)
          (= "" text))
    (db/get-stan)
    (db/search-stan text)))

(defn authenticated-admin? [session]
  (and (authenticated? session)
       (="admin" (:rola (:identity session)))))

(defn get-search-stanovi [params session]
   (cond
    (not (authenticated? session))
    (redirect "/login")
    (authenticated-admin? session)
    (render-file "views/stan-search-admin.html" {:title "Prikaz stanova"
                                                 :logged (:identity session)
                                                 :stanovi (get-stanovi nil)})
    :else
    (render-file "views/stan-search.html" {:title "Search stan"
                                           :logged (:identity session)
                                           :stanovi (get-stanovi nil)})))

(defn get-search-stanovi-vl [params session]
  (render-file "views/stan-search-vl.html" {:title "Search stan"
                                             :logged (:identity session)
                                             :stanovi (get-stanovi-vl nil)}))

(defresource search-stan [{:keys [params session]}]
  :allowed-methods [:post]
  :handle-created (json/write-str (get-stanovi (:text params)))
  :available-media-types ["application/json"])

(defresource search-stan-vl [{:keys [params session]}]
  :allowed-methods [:post]
  :handle-created (json/write-str (get-stanovi-vl (:text params)))
  :available-media-types ["application/json"])

(defresource search-stan [{:keys [params session]}]
  :allowed-methods [:get]
  :available-media-types ["text/html" "application/json"]
  :handle-ok #(let [media-type (get-in % [:representation :media-type])]
                (condp = media-type
                  "text/html" (get-search-stanovi params session)
                  "application/json" (->(:text params)
                                        (get-stanovi)
                                        (json/write-str)))))

(defresource search-stan-vl [{:keys [params session]}]
  :allowed-methods [:post]
  :authenticated? (authenticated? session)
  :handle-created (json/write-str (get-stanovi-vl (:text params)))
  :available-media-types ["application/json"])

(defresource search-stan-vl [{:keys [params session]}]
  :allowed-methods [:get]
  :available-media-types ["text/html" "application/json"]
  :handle-ok #(let [media-type (get-in % [:representation :media-type])]
                (condp = media-type
                  "text/html" (get-search-stanovi-vl params session)
                  "application/json" (->(:text params)
                                        (get-stanovi-vl)
                                        (json/write-str)))))

(defn get-add-stan-page [session &[message]]
  (if-not (authenticated? session)
    (redirect "/vlogin")
    (render-file "views/stan-add.html" {:title "Kreiraj stan"
                                        :logged (:identity session)})))

(defn add-stan [{:keys [params session]}]
    (stan-validation? params)
    (println params)
    (db/add-stan params)
    (redirect "/vlasnikForma"))

(defn get-stan-edit-page [page params session]
  (render-file page {:title "Stan"
                     :logged (:identity session)
                     :stan (first (db/find-stan params))}))

(defn get-stan [{:keys [params session]}]
   (if-not (authenticated? session)
    (redirect "/vlogin")
    (get-stan-edit-page "views/edit-stan.html" params session)))

(defresource update-stan [{:keys [params session]}]
  :allowed-methods [:put]  
  :available-media-types ["application/json"]
  (println params)
  (db/update-stan params))


(defresource delete-stan [{:keys [params session]}]
  :allowed-methods [:delete] 
  (db/delete-favorit-stan (:stanID params))
  (db/delete-stan (:stanID params))
  :available-media-types ["application/json"])


(defroutes stan-routes
  (GET "/stanovi" request (stanovi (:session request)))  
  (GET "/pretraga" request (search-stan request))
  (GET "/pretragavl" request (search-stan-vl request))
  (GET "/addstan" request (get-add-stan-page (:session request)))
  (POST "/addstan" request (add-stan request))
  (GET "/stan/:stanID" request (get-stan request))
  (PUT "/stan" request (update-stan request))  
  (DELETE "/stan" request (delete-stan request)))

