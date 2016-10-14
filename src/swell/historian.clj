(ns swell.historian
  (:require [clojure.data :refer (diff)]))

(defn augment-history
  "Adds the observation to history if (pred observation
  last-observation) is truthy. Caps the history at 25 items."
  [history observation pred]
  (if (pred observation (first history))
    (take 25 (cons observation history))
    history))

(defn different-observations
  [step-1 step-2]
  (not= (:observation step-1) (:observation step-2)))

(defn augment-step-history
  [history step]
  (augment-history history step different-observations))

(defn explain-step-history
  [history]
  (for [[step prev] (partition 2 1 history)]
    (let [[added removed _] (diff (:observation step) (:observation prev))]
      {:step step
       :added added
       :removed removed})))
