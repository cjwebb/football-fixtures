(ns football-fixtures.test.handler
  (:use clojure.test
        ring.mock.request  
        football-fixtures.handler))

(deftest test-app
  (testing "main route"
    (let [response (app (request :get "/fixtures"))]
      (is (= (:status response) 200))
      (is (= (:body response) "{\"hello\":\"world\"}"))))
  
  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404)))))
