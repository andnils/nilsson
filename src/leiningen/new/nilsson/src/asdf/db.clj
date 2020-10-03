(ns {{name}}.db
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.spec.alpha :as s]
            [clojure.tools.logging :as log]
            [honeysql.core :as sql]
            [honeysql.helpers :refer [select from]]))

(s/def ::id pos-int?)
(s/def ::name string?)
(s/def ::project (s/keys :req-un [::name]
                         :opt-un [::id]))


(defn list-projects [db]
  (jdbc/query db (-> (select :id :name)
                     (from :project)
                     (sql/format))))

(defn create-project [db project]
  (log/debug project)
  (if (s/valid? ::project project)
    (jdbc/insert! db :project project)
    (s/explain-data ::project project)))


(defn find-project-by-id [db id]
  (jdbc/get-by-id db :project id))

(comment
  (s/valid? ::project {:id 2 :name 2})
  (s/conform ::project {:id 2 :name 2})
  (s/explain-data ::project {}))
