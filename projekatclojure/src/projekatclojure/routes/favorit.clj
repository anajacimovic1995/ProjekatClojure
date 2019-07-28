(ns projekatclojure.routes.favorit
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

(def favorit-schema
  {:userID [st/required st/number]
   :stanID [st/required st/number]})

(defn stan-validation? [params]
  (st/valid? {:userID (read-string (:userID params))
              :stanID (read-string (:stanID params))} favorit-schema))
  
(defn get-favoriti-page [page session]
  (render-file page
               {:title "Favoriti"
                :logged (:identity session)
                :favoriti (db/get-favorit)}))

(defn favoriti [session]
    (get-favoriti-page "views/favoriti.html" session))

(defn add-favorit-to-db [favorit]
  (-> (db/add-favorit favorit)))


(defn favorit-page-submit [{:keys [params session]}]
   (add-favorit-to-db params)
   (redirect "/stanovi"))


(defroutes favorit-routes
  (GET "/favoriti" request (favoriti (:session request)))
  (POST "/stanovi" request (favorit-page-submit request)))   
  
