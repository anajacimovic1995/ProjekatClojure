(ns projekatclojure.routes.userforma
  (:require [compojure.core :refer :all]
            [selmer.parser :refer [render-file]]
            [compojure.response :refer [render]]
            [buddy.auth :refer [authenticated?]]
            [projekatclojure.models.komunikacija :as db]
            [ring.util.response :refer [redirect]]))



(defn get-userforma-page [page session]
  (render-file page
               {:title "Glavna user forma"
                :logged (:identity session)}))

(defn userforma [session]
  (cond
    (not (authenticated? session))
    (redirect "/login")
    :else
    (get-userforma-page "views/userForma.html" session)))

(defroutes forme-routes
  (GET "/userForma" request (userforma (:session request))))