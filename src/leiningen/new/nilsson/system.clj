(ns {{name}}.system
  (:gen-class)
  (:require [com.stuartsierra.component :as component]
            [environ.core :refer [env]]
            [ragtime.jdbc]
            [ragtime.repl]
            [{{name}}.component.hikaricp :refer [hikaricp]]
            [{{name}}.component.jetty :refer [jetty-server]]))


(defn- -make-config [db-url db-user db-password http-port]
  {:http-config {:port (or http-port "3000")}
   :db-config {:jdbc-url db-url
               :username db-user
               :password db-password}
   :ragtime-config {:datastore  (ragtime.jdbc/sql-database {:connection-uri db-url
                                                            :user db-user
                                                            :password db-password})
                    :migrations (ragtime.jdbc/load-resources "migrations")}})


(defn make-config
  "Pull the configs from the env map"
  []
  (let [{:keys [http-port db-url db-user db-password]} env]
    (-make-config db-url db-user db-password http-port)))




(defn make-system
  "Construct the system.
  Either pull the config from the environment, or supply
  a config."
  ([]
   (let [config (make-config)]
     (make-system config)))
  ([config]
   (let [{:keys [db-config http-config]} config]
     (component/system-map
       :db (hikaricp db-config)
       :http (component/using
               (jetty-server http-config) [:db])))))


(defn db-migrate [config]
  (ragtime.repl/migrate (:ragtime-config config)))

(defn db-rollback [config]
  (ragtime.repl/rollback (:ragtime-config config)))

(defn -main [& args]
  (let [config (make-config)
        system (make-system config)]
    (db-migrate config)
    (component/start system)))
