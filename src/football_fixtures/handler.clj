(ns football-fixtures.handler
  (:use compojure.core)
  (:use ring.util.response)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :as middleware]))

(defn fixtures [_]
  {:body {:hello "world"}})

(defroutes app-routes
  (GET "/fixtures" [] fixtures)
  (route/not-found {:body nil}))

(def app
  (-> (handler/site app-routes)
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)))
