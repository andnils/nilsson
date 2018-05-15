(ns dev
  "Tools for interactive development with the REPL. This file should
  not be included in a production build of the application.

  Call `(reset)` to reload modified code and (re)start the system.

  The system under development is `system`, referred from
  `com.stuartsierra.component.repl/system`.

  See also https://github.com/stuartsierra/component.repl"
  (:require
   [clojure.java.io :as io]
   [clojure.java.javadoc :refer [javadoc]]
   [clojure.pprint :refer [pprint]]
   [clojure.reflect :refer [reflect]]
   [clojure.repl :refer [apropos dir doc find-doc pst source]]
   [clojure.set :as set]
   [clojure.string :as string]
   [clojure.test :as test]
   [clojure.tools.namespace.repl :refer [refresh refresh-all clear]]
   [com.stuartsierra.component :as component]
   [com.stuartsierra.component.repl :refer [reset set-init start stop system]]
   [ragtime.jdbc]
   [ragtime.repl]
   [{{name}}.system]))


(clojure.tools.namespace.repl/set-refresh-dirs "dev" "src/main/clj" "src/test/clj")


(def dev-config ({{name}}.system/make-config))

(defn db-migrate []
  ({{name}}.system/db-migrate dev-config))
(defn db-rollback []
  ({{name}}.system/db-rollback dev-config))


(set-init (fn [_] ({{name}}.system/make-system dev-config)))