(ns swell.collectors
  (:require [environ.core :refer (env)]
            [swell.collectors.http-endpoint :as http-endpoint]
            [swell.collectors.cloudwatch :as cloudwatch]))

(defn collect
  []
  (case (env :monitor-mode)

    "cloudwatch"
    (cloudwatch/collect)

    "http-endpoint"
    (http-endpoint/collect)

    {:status :error,
     :description (str
                   "swell error: MONITOR_MODE environment variable not set or recognised:"
                   (env :monitor-mode))}))
