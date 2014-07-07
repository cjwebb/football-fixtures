(ns football-fixtures.test.handler
  (:use clojure.test
        ring.mock.request  
        football-fixtures.handler
        football-fixtures.fixtures))

(deftest test-app
  (testing "main route returns fixtures-atom"
    (let [fixture-list (reset! fixtures-atom {:hello "world"})
          response (app (request :get "/fixtures"))]
      (is (= (:status response) 200))
      (is (= (:body response) "{\"hello\":\"world\"}"))))
  
  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404)))))
