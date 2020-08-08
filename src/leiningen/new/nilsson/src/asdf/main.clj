(ns {{name}}.main
  (:require [{{name}}.system :refer [system-config]]
            [juxt.clip.core :as clip]))

(def system nil)

(defn -main [& _]
  (let [system-config (system-config :prod)
        system (clip/start system-config)]
    (alter-var-root #'system (constantly system))
    (.addShutdownHook
     (Runtime/getRuntime)
     (Thread. #(clip/stop system-config system))))
  @(promise))








