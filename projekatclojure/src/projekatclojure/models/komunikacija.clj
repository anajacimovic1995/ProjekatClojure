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
  (k/where {:userID id})))

(defn find-user [params]
  (k/select user
            (k/where params)))

(defn find-user-by-id [params]
  (k/select user
            (k/where params)))

(defn get-users []
  (k/select user
  (k/where {:rola "kupac"})))

(defn update-user [params]
  (k/update user
            (k/set-fields params)
            (k/where {:id (:id params)})))

(defn search-user [text]
  (k/select user
            (k/where (or
                       {:userID text}
                       {:imePrezime text}
                       {:username text}
                       {:password text}
                       {:email text}
                       {:rola text}))
            (k/order :userID :ASC)))

(k/defentity stan
  (k/table :stan))

(defn get-stan []
  (k/select stan
          (k/fields :* [:vlasnik.imePrezime :vime])
          (k/join vlasnik (= :stan.prodavacID :vlasnik.vlasnikID))))

(defn search-stan [text]
  (k/select stan
            (k/fields :* [:vlasnik.imePrezime :vime])
            (k/join vlasnik (= :stan.prodavacID :vlasnik.vlasnikID) )
            (k/where (or
                       {:stanID text}
                       {:sajt text}
                       {:kvadratura text}
                       {:cenaEvri text}
                       {:lokacija text}
                       {:vlasnik.imePrezime text}))
            (k/order :stanID :ASC)))

(defn add-stan [params]
  (k/insert stan
  (k/values params)))

(defn delete-stan [id]
  (k/delete stan
  (k/where {:stanID id})))

(defn find-stan [params]
  (k/select stan
          (k/fields :* [:vlasnik.imePrezime :vime])
          (k/join vlasnik (= :stan.prodavacID :vlasnik.vlasnikID))
          (k/where params)))

(defn find-stan-by-id [id]
  (k/select stan
          (k/fields :* [:vlasnik.imePrezime :vime])
          (k/join vlasnik (= :stan.prodavacID :vlasnik.vlasnikID))
          (k/where {:stanID id})))

(defn update-stan [params]
  (k/update stan
            (k/set-fields params)
            (k/where {:stanID (:stanID params)})))

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
  (k/where {:projekatID id})))

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
   (k/select favorit
          (k/fields :* [:stan.lokacija :slokacija])
          (k/join stan (= :favorit.stanID :stan.stanID))))

(defn add-favorit [params]
  (k/insert favorit
  (k/values params)))

(defn delete-favorit [id]
  (k/delete favorit
  (k/where {:favoritID id})))

(defn delete-favorit-stan [stan]
  (k/delete favorit
  (k/where {:stanID stan})))

(defn get-vlasnik []
  (k/select vlasnik))

(defn search-vlasnik [text]
  (k/select vlasnik
            (k/where (or
                       {:vlasnikID text}
                       {:imePrezime text}
                       {:adresa text}
                       {:kontakt text}
                       {:username text}
                       {:password text}))
            (k/order :vlasnikID :ASC)))

(defn add-vlasnik [params]
  (k/insert vlasnik
  (k/values params)))

(defn delete-vlasnik [id]
  (k/delete vlasnik
  (k/where {:vlasnikID id})))

(defn find-vlasnik [params]
  (k/select vlasnik
            (k/where params)))

(defn find-vlasnik-by-id [params]
  (k/select vlasnik
            (k/where params)))
