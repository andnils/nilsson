(ns {{name}}.system
    (:gen-class)
    (:require [com.stuartsierra.component :as component]
              [aero.core :as aero]
              [{{name}}.database-migrations :refer [db-migrate]]
              [{{name}}.components.c3p0 :refer [connection-pool]]
              [{{name}}.components.jetty :refer [jetty-server]]))


(defn make-config []
  (aero/read-config
   (clojure.java.io/resource "config.edn")
   {:resolver aero/relative-resolver}))


(defn make-system [config]
  (component/system-map
   :db (connection-pool config)
   :http (component/using (jetty-server config) [:db])))


(defn -main [& args]
  (let [config (make-config)
        system (make-system config)]
    (db-migrate config)
    (component/start system)))


