(ns swell.collectors.http-endpoint
  (:require [environ.core :refer (env)]
            [clj-http.client :as http-client]))

(defn collect
  []
  (:body (http-client/get (env :http-endpoint))))
