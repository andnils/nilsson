(ns {{name}}.components.c3p0
    (:require [com.stuartsierra.component :as component])
    (:import com.mchange.v2.c3p0.ComboPooledDataSource))

(defn- pool [spec]
  (doto (ComboPooledDataSource.)
    (.setJdbcUrl (:connection-uri spec))
    (.setUser (:user spec))
    (.setPassword (:password spec))          
    (.setMaxIdleTimeExcessConnections 60)
    (.setMaxIdleTime 20)))


(defrecord C3P0 [config]
  component/Lifecycle
  (start [component]
    (if (:datasource component)
      component
      (assoc component :datasource (pool config))))

  (stop [component]
    (if-let [ds (:datasource component)]
      (do (.close ds)
          (dissoc component :datasource))
      component)))


(defn connection-pool [config]
  (->C3P0 config))
