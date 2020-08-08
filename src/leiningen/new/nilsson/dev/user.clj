(ns user
  (:require [juxt.clip.repl :refer [start stop reset set-init! system]]
            [{{name}}.system :refer [system-config]]))

(set-init! #(system-config :dev))

