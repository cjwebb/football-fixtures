(defproject football-fixtures "0.1.0-SNAPSHOT"
  :description "Scrapes Football Fixtures, and regularly updates when they are rescheduled."
  :url "https://github.com/cjwebb/football-fixtures"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]
                 [ring/ring-json "0.3.1" :exclusions [ring/ring-core]]]
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler football-fixtures.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
