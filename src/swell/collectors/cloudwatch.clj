(ns swell.collectors.cloudwatch
  (:require [environ.core :refer (env)]
            [amazonica.aws.cloudwatch :refer (describe-alarms)]))

(defn collect
  []
  (let [snapshot (describe-alarms {:endpoint (env :cloudwatch-aws-region)})]
    (sort-by
     :alarm-arn
     (for [metric-alarm (:metric-alarms snapshot)]
       (select-keys metric-alarm [:alarm-arn :alarm-name :state-value])))))
