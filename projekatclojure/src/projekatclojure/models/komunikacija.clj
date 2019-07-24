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

(k/defentity vlasnik
  (k/table :vlasnik))

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

(defn get-stan []
  (k/select stan))

(defn add-stan [params]
  (k/insert stan
  (k/values params)))

(defn delete-stan [id]
  (k/delete stan
  (k/where {:id id})))

(defn find-stan [params]
  (k/select stan
            (k/where params)))

(k/defentity projekat
  (k/table :projekat))

(defn get-projekti []
  (k/select projekat
          (k/fields :* [:vlasnik.imePrezime :vime])
          (k/join vlasnik (= :projekat.investitorID :vlasnik.vlasnikID))))

(defn add-projekat [params]
  (k/insert projekat
  (k/values params)))

(defn delete-projekat [id]
  (k/delete projekat
  (k/where {:id id})))

(defn find-projekat [params]
  (k/select projekat
          (k/fields :* [:vlasnik.imePrezime :vime])
          (k/join vlasnik (= :projekat.investitorID :vlasnik.vlasnikID))
          (k/where params)))

(defn get-text-search [text]
  (str "%" text "%"))

(defn search-projekti [text]
  (k/select projekat
            (k/fields :* [:vlasnik.imePrezime :vime])
            (k/join vlasnik (= :projekat.investitorID :vlasnik.vlasnikID))
            (k/where (or
                       {:projekatID text}
                       {:tip text}
                       {:naziv text}
                       {:lokacija text}
                       {:opis text}
                       {:godinaZavrsetka [like (get-text-search text)]}
                       {:vlasnik.imePrezime [like (get-text-search text)]}))
            (k/order :projekatID :ASC)))

(k/defentity favorit
  (k/table :favorit))

(defn get-favorit []
  (k/select favorit))

(defn add-favorit [params]
  (k/insert favorit
  (k/values params)))

(defn delete-favorit [id]
  (k/delete favorit
  (k/where {:id id})))

(defn find-favorit [params]
  (k/select favorit
            (k/where params)))



(defn get-vlasnik []
  (k/select vlasnik))

(defn add-vlasnik [params]
  (k/insert vlasnik
  (k/values params)))

(defn delete-vlasnik [id]
  (k/delete vlasnik
  (k/where {:id id})))

(defn find-vlasnik [params]
  (k/select vlasnik
            (k/where params)))
