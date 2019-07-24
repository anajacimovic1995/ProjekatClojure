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
                :stanovi (db/get-stan)}))

(defn stanovi [session]
    (get-stanovi-page "views/stanovi.html" session))

(defroutes stan-routes
  (GET "/stanovi" request (stanovi (:session request))))
