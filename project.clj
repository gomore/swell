(defproject swell "0.1.0-SNAPSHOT"
  :description "swells up metrics"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [amazonica "0.3.76"]
                 [compojure "1.5.1"]
                 [hiccup "1.0.5"]
                 [environ "1.1.0"]
                 [clj-http "2.3.0"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:init swell.ring/init
         :handler swell.ring/handler}
  :profiles {:dev {:source-paths ["./dev"]
                   :dependencies [[ring/ring-jetty-adapter "1.5.0"]
                                  [ring/ring-devel "1.5.0"]
                                  [org.clojure/tools.namespace "0.2.11"]]}})
