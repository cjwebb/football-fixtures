(ns football-fixtures.fixtures
  (:require [org.httpkit.client :as http-kit]
            [football-fixtures.scraping :as scraping]))

(def fixtures-atom
  (atom ()))

(def premier-league-fixtures
  "http://www.premierleague.com/en-gb/matchday/matches.html?paramClubId=ALL&paramComp_100=true&view=.dateSeason")

(defn fetch-fixtures []
  (let [html (http-kit/get premier-league-fixtures)
        fixtures (scraping/scrape-fixtures (:body @html))]
    (reset! fixtures-atom fixtures)))

