(ns football-fixtures.scraping
  (:require [net.cgrand.enlive-html :as html]))

(defn- content-tables [snippet]
  (html/select (html/html-snippet snippet) [:table.contentTable]))

(defn- split-clubs [clubs]
  (clojure.string/split clubs #" v "))

(defn- extract-matches [node]
  (let [time      (first (html/select [node] [[:td.time]]))
        clubs     (first (html/select [node] [[:td.clubs] [:a]]))
        venue     (first (html/select [node] [[:td.location] [:a]]))
        home-team (first (split-clubs (html/text clubs)))
        away-team (second (split-clubs (html/text clubs)))
        fixture   (map html/text [time home-team away-team venue])]
    (zipmap [:time :home-team :away-team :venue] fixture)))

(defn- extract [node]
  (let [match-date (map html/text (html/select [node] [[:tr html/first-of-type] [:th]]))
        matches    (drop 1 (html/select [node] [:tr]))]
    (map (fn [x] (assoc x :date (clojure.string/trim(first match-date)))) (map extract-matches matches))))

(defn scrape-fixtures [snippet]
  (flatten (map extract (content-tables snippet))))

