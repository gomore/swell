(ns swell.state-machine
  (:require [clojure.set :refer (difference)]))

(defn initial-state
  [available-pictures]
  {:pictures available-pictures
   :associations {}
   :current-picture nil
   :observation nil})

(defn step
  [{:keys [pictures associations history current-picture] :as state}
   observation
   timestamp]
  (let [remaining-pictures (vec (difference (set pictures) (set (vals associations))))
        next-picture (get associations observation (rand-nth remaining-pictures))]
    {:pictures pictures
     :current-picture next-picture
     :associations (assoc associations observation next-picture)
     :observation observation
     :timestamp timestamp}))
