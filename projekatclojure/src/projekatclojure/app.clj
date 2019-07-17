(ns projekatclojure.app
  (:require [ring.adapter.jetty :as jetty]))

(defn app [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello Clojure, Hello Ring!"})

(defn -main []
  (jetty/run-jetty :handler {:port 8080}))



