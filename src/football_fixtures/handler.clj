(ns football-fixtures.handler
  (:use compojure.core)
  (:use ring.util.response)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :as middleware]
            [org.httpkit.client :as http-kit]
            [football-fixtures.fixtures :as fixtures]))

(defn get-fixtures [_]
  {:body @fixtures/fixtures-atom})

(defroutes app-routes
  (GET "/fixtures" [] get-fixtures)
  (route/not-found {:body nil}))

(def app
  (-> (handler/site app-routes)
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)))
