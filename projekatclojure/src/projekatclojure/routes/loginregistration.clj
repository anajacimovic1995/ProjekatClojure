(ns projekatclojure.routes.loginregistration  
  (:require [compojure.core :refer :all]
            [bcrypt-clj.auth :as bcrypt]
            [struct.core :as st]
            [ring.util.response :refer [redirect]]
            [selmer.parser :refer [render-file]]
            [projekatclojure.models.komunikacija :as db]))

(def login-schema
  {:username [st/required st/string]
   :password [st/required st/string]})

(defn login-validation? [params]
  (st/valid? {:username (:username params)
              :password (:password params)} login-schema))

(defn get-login-page [&[error]]
  (render-file "views/login.html" {:title "Logovanje"
                                   :error error}))

(defn get-user-by-username-from-db [params]
  (-> (select-keys params [:username])
      (db/find-user)
      (first)))

(defn login-page-submit [{:keys [params session]}]
  (let [user (get-user-by-username-from-db params)]
    (cond
      (not (login-validation? params))
      (or (empty? user)
          (not (bcrypt/check-password (:password params) (:password user))))
      (get-login-page "Neispravno logovanje!")
      (get-login-page "Unesite svoj username i password")
      :else
      (do (assoc (redirect "/"):session (assoc session :identity user))))))

(defn logout [request]
  (-> (redirect "/login")
      (assoc :session {})))

(defroutes log-routes
           (GET "/login" [] (get-login-page))
           (POST "/login" request (login-page-submit request))
           (GET "/logout" request (logout request)))