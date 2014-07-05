(ns football-fixtures.job 
  (:require [clojurewerkz.quartzite.scheduler :as qs]
            [clojurewerkz.quartzite.triggers :as t]
            [clojurewerkz.quartzite.jobs :as j]
            [clojurewerkz.quartzite.jobs :refer [defjob]]
            [clojurewerkz.quartzite.schedule.calendar-interval :refer [schedule with-interval-in-days]]
            [football-fixtures.fixtures :as fixtures]))

(defjob FetchFixturesJob [ctx]
  (fixtures/fetch-fixtures)
  (println "Fixtures fetched " (.toString (new java.util.Date))))

(defn scheduler []
  "This is just copied from http://clojurequartz.info/articles/getting_started.html#using_calendar_interval_schedules"
  (qs/initialize)
  (qs/start)
  (let [job (j/build
              (j/of-type FetchFixturesJob)
              (j/with-identity (j/key "jobs.fetch-fixtures.1")))
        trigger (t/build
                  (t/with-identity (t/key "triggers.1"))
                  (t/start-now)
                  (t/with-schedule (schedule (with-interval-in-days 7))))]
              (qs/schedule job trigger)))

