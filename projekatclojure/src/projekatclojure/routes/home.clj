(ns projekatclojure.routes.home  
  (:require [compojure.core :refer :all]
            [projekatclojure.models.komunikacija :as db]
            [selmer.parser :refer [render-file]]
            [compojure.response :refer [render]]
            [selmer.parser :refer [render-file]]
            [liberator.core :refer [defresource]]
            [clojure.data.json :as json]
            [struct.core :as st]
            [clojure.java.io :as io]
            [liberator.representation :refer [ring-response as-response]]
            [clojure.set :refer [rename-keys]]
            [clojure.string :as str]
            [ring.util.response :refer [redirect]]))

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

(defn get-home-page [page session]
  (render-file page
               {:title "Home"
                :kupci (count (db/get-users))
                :vlasnici (count (db/get-vlasnik))
                :stanovi (count (db/get-stan))}))

(defn home-page [session]
  (get-home-page "views/home.html" session))

(defn send-submit [{:keys [params session]}]
  
  (get-home-page "views/home.html" session))

(defn get-stanovi [text]
  (if (or (nil? text)
          (= "" text))
    (db/get-stan)
    (db/search-stan text)))

(defn get-search-stanovi [params session]
    (render-file "views/search-stan-home.html" {:title "Home page"
	                                              :stanovi (get-stanovi nil)}))

(defresource search-stan-home [{:keys [params session]}]
  :allowed-methods [:post]
  :handle-created (json/write-str (get-stanovi (:text params)))
  :available-media-types ["application/json"])

(defresource search-stan-home [{:keys [params session]}]
  :allowed-methods [:get]
  :available-media-types ["text/html" "application/json"]
  :handle-ok #(let [media-type (get-in % [:representation :media-type])]
                (condp = media-type
                  "text/html" (get-search-stanovi params session)
                  "application/json" (->(:text params)
                                        (get-stanovi)
                                        (json/write-str)))))
(defn get-projekti [text]
  (if (or (nil? text)
          (= "" text))
    (db/get-projekti)
    (db/search-projekti text)))

(defn get-search-projekti [params session]
  (render-file "views/search-projekat-home.html" {:title "Home page"
                                                  :projekti (get-projekti nil)}))

(defresource search-projekat-home [{:keys [params session]}]
  :allowed-methods [:post]
  :handle-created (json/write-str (get-projekti (:text params)))
  :available-media-types ["application/json"])

(defresource search-projekat-home [{:keys [params session]}]
  :allowed-methods [:get]
  :available-media-types ["text/html" "application/json"]
  :handle-ok #(let [media-type (get-in % [:representation :media-type])]
                (condp = media-type
                  "text/html" (get-search-projekti params session)
                  "application/json" (->(:text params)
                                        (get-projekti)
                                        (json/write-str)))))


(defroutes home-routes
           (GET "/" request (home-page (:session request)))
           (POST "/" request (send-submit request))
           (GET "/pretragahome" request (search-stan-home request))
           (GET "/pretragaprojekatahome" request (search-projekat-home request)))





