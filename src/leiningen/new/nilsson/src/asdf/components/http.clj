(ns {{name}}.components.http
  (:require [ring.adapter.jetty :refer [run-jetty]]))


(defn start [{:keys [handler] :as config}]
  (run-jetty handler (merge {:join? false}
                            (dissoc config :handler))))

(defn stop [jetty]
  (.stop jetty)
  (.join jetty))

