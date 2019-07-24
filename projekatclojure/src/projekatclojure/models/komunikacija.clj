(ns projekatclojure.models.komunikacija
  (:require [clojure.java.jdbc :as sql]
            [korma.core :as k]
            [korma.db :refer [defdb mysql]]
            [clj-time.coerce :as c]
            [clj-time.core :as t])
  (:import java.sql.DriverManager))

(def db-config(clojure.edn/read-string (slurp "config.edn")))

(defdb db (mysql db-config))

(k/defentity user
  (k/table :user))

(defn add-user [params]
  (k/insert user
  (k/values params)))

(defn delete-user [id]
  (k/delete user
  (k/where {:id id})))

(defn find-user [params]
  (k/select user
            (k/where params)))

(defn get-users []
  (k/select user
  (k/where {:rola "obican"})))

(defn update-user [params]
  (k/update user
            (k/set-fields params)
            (k/where {:id (:id params)})))

(k/defentity stan
  (k/table :stan))

(k/defentity projekat
  (k/table :projekat))

(defn get-projekat []
  (k/select projekat))

(k/defentity favorit
  (k/table :favorit))