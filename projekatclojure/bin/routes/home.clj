(ns routes.home  
  (:require [compojure.core :refer :all]))

(defn home-page
  [session]
    (layout/render "home.html"))

(defroutes home-routes
           (GET "/" request (home-page (:session request))))