(ns projekatclojure.routes.userforma
  (:require [compojure.core :refer :all]
            [selmer.parser :refer [render-file]]
            [compojure.response :refer [render]]
            [buddy.auth :refer [authenticated?]]
            [projekatclojure.models.komunikacija :as db]
            [ring.util.response :refer [redirect]]
            [struct.core :as st]))

(def vlasnik-schema
  {:imePrezime [st/required st/string]
   :adresa [st/required st/string]
   :kontakt [st/required st/string]
   :username [st/required st/string]
   :password [st/required st/string]})

(defn vlasnik-validation? [params]
  (st/valid? {:imePrezime (:imePrezime params)
              :adresa (:adresa params)
              :kontakt (:kontakt params)
              :username (:username params)
              :password (:password params)} vlasnik-schema))

(defn authenticated-admin? [session]
  (and (authenticated? session)
       (="admin" (:rola (:identity session)))))

(defn get-userforma-page [page session]
  (render-file page
               {:title "Glavna user forma"
                :logged (:identity session)}))

(defn userforma [session]
  (cond
    (not (authenticated? session))
    (redirect "/login")
    (authenticated-admin? session)
    (get-userforma-page "views/adminForma.html" session)
    :else
    (get-userforma-page "views/userForma.html" session)))

(defn get-vlasnikforma-page [page session]
  (render-file page
               {:title "Glavna forma vlasnika"
                :logged (:identity session)}))

(defn vlasnikforma [session]
  (cond
    (not (authenticated? session))
    (redirect "/vlogin")
    :else
    (get-vlasnikforma-page "views/vlasnikForma.html" session)))

(defresource search-user [{:keys [params session]}]
  :allowed-methods [:post]
  :handle-created (json/write-str (get-useri (:text params)))
  :available-media-types ["application/json"])

(defroutes forme-routes
  (GET "/userForma" request (userforma (:session request)))
  (GET "/vlasnikForma" request (vlasnikforma (:session request))))