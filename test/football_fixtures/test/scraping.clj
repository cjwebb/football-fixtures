(ns football-fixtures.test.scraping
  (:use clojure.test
        football-fixtures.scraping))

(defn snippet [resource-name]
  (slurp (clojure.java.io/resource resource-name)))

(def expected-fixtures
  '({:home-team "Arsenal" :away-team "Crystal Palace" :date "Saturday 16 August 2014" :time "15:00" :venue "Emirates Stadium"}
    {:home-team "Burnley" :away-team "Chelsea" :date "Saturday 16 August 2014" :time "15:00" :venue "Turf Moor"}
    {:home-team "Leicester" :away-team "Everton" :date "Saturday 16 August 2014" :time "15:00" :venue "King Power Stadium"}))

(deftest test-scraping
  (testing "scrape-fixtures"
    (let [fixtures-html (snippet "matches.html")
          actual-fixtures (scrape-fixtures fixtures-html)]
      (is (= (take 3 actual-fixtures) expected-fixtures)))))

