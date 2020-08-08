(ns {{name}}.routes
  (:require [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.middleware.params :refer [wrap-params]]
            [compojure.coercions :refer [as-int]]
            [compojure.core :refer [GET PUT POST DELETE routes context]]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [clojure.tools.logging :as log]
            [clojure.string :as str]))


(defn find-stuff [db id]
  (resp/response
   {:id id
    :name "Cheeze"}))

(defn list-stuff [db]
  (resp/response
   [{:id 1
     :name "Cheeze"}
    {:id 2
     :name "Ost"}]))

(defn app-routes [db]
  (routes
   (GET "/api/stuff/:id" [id :<< as-int]
        (find-stuff db id))
   (GET "/api/stuff" []
        (list-stuff db))
   (route/not-found {:body {:error :not-found}})
))


(defn wrap-logger [handler]
  (fn [{:keys [request-method uri] :as request}]
    (log/info (format "%s %s" (-> request-method name str/upper-case) uri))
    (handler request)))

(defn handler [db]
  (-> (app-routes db)
      (wrap-params)
      (wrap-json-body {:keywords? true})
      (wrap-json-response)
      (wrap-logger)))



