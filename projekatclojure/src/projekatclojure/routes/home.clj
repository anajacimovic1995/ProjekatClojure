(ns projekatclojure.routes.home  
  (:require [compojure.core :refer :all]
            [projekatclojure.models.komunikacija :as db]
            [selmer.parser :refer [render-file]]
            [compojure.response :refer [render]]))

(defn get-home-page [page session]
  (render-file page
               {:title "Home"
                :kupci (count (db/get-users))
                :vlasnici (count (db/get-vlasnik))
                :stanovi (count (db/get-stan))}))

(defn home-page
  [session]
  (get-home-page "views/home.html" session))

(defroutes home-routes
           (GET "/" request (home-page (:session request))))





