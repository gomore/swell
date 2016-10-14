(ns swell.curator
  (:require [environ.core :refer (env)]
            [clj-http.client :as http-client]
            [clojure.string :refer (split-lines)]))

(defn list-pictures
  []
  (-> (env :pictures-url)
      http-client/get
      :body
      split-lines))
