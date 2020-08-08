(ns {{name}}.system
  (:require [clojure.java.io :as io]
            [aero.core :refer [read-config]]))

(defn system-config [profile]
  (read-config
   (io/resource "config.edn")
   {:profile profile}))
