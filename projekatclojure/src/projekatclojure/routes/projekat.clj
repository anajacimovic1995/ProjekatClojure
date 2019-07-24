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
              :godinaZavrsetka (read-string (:godinaZavrsetka params))
              :investitorID (read-string (:vlasnik params))} projekat-schema))

(defn get-projekti-page [page session]
  (render-file page
               {:title "Projekti"
                :logged (:identity session)
                :projekti (db/get-projekti)}))

(defn projekti [session]
    (get-projekti-page "views/projekti.html" session))

(defroutes projekat-routes
  (GET "/projekti" request (projekti (:session request))))