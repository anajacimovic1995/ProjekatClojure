(ns projekatclojure.app
  (:require [org.httpkit.server :refer [run-server]]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]            
            [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]
            [routes.home :refer [home-routes]]
            [ring.middleware.webjars :refer [wrap-webjars]]
            [ring.middleware.flash :refer [wrap-flash]]
            [ring.middleware.resource :refer [wrap-resource]]
            [buddy.auth.backends.session :refer [session-backend]]
            [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]
            [buddy.auth.accessrules :refer [wrap-access-rules success error]]
            [ring.middleware.session :refer [wrap-session]]
            [ring.middleware.params :refer [wrap-params]]
            [buddy.auth :refer [authenticated?]]
            [buddy.auth.accessrules :refer [restrict]]
            [ring.middleware.json :refer [wrap-json-response]]))

(defn start [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello Clojure!"})

(defn on-error [request response]
  {:status  403
   :headers {"Content-Type" "text/plain"}
   :body    (str "Access to " (:uri request) " is not authorized")})

(def app
  (-> (routes
        home-routes
        (wrap-routes wrap-defaults api-defaults))
      (wrap-json-response)
      (handler/site)
      (wrap-authentication backend)
      (wrap-authorization backend)
      (wrap-flash)
      (wrap-params)
      (wrap-webjars)
      (wrap-resource "public")))


(defn -main []
  
  (run-server start {:port 8080})
  (println "Server started on port 8080"))





