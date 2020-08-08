(ns {{name}}.components.db
  (:import com.mchange.v2.c3p0.ComboPooledDataSource))


(defn start [config]
  (let [datasource (doto (ComboPooledDataSource.)
                     (.setJdbcUrl (:connection-uri config))
                     (.setUser (:user config))
                     (.setPassword (:password config))
                     (.setMaxIdleTimeExcessConnections 60)
                     (.setMaxIdleTime 20))]
    {:datasource datasource}))


(defn stop [{:keys [datasource]}]
  (.close datasource))


