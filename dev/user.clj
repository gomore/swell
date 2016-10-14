(ns user
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.stacktrace :refer (wrap-stacktrace)]
            [clojure.tools.namespace.repl :refer (refresh)]
            [swell.ring :refer [init handler]]))

(defonce server nil)

(defn start []
  (alter-var-root
   #'server
   (fn [_]
     (init)
     (jetty/run-jetty
      (wrap-stacktrace handler)
      {:port 8000 :join? false}))))

(defn stop []
  (alter-var-root
   #'server
   (fn [server]
     (when server
       (.stop server)))))

(defn reset []
  (stop)
  (refresh :after 'user/start))
