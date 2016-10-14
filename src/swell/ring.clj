(ns swell.ring
  (:require [compojure.core :refer [defroutes GET HEAD]]
            [compojure.route :as route]
            [ring.middleware.resource :refer (wrap-resource)]
            [swell.curator :as curator]
            [swell.state-machine :as state-machine]
            [swell.historian :as historian]
            [swell.collectors :as collectors]
            [swell.html :as html]
            [clojure.java.io :as io])
  (:import [java.time Instant]))

;; Combined state of the app - step and history

(def state-atom (atom nil))

(defn init
  []
  (reset!
   state-atom
   {:step (state-machine/initial-state (curator/list-pictures))
    :history ()}))

(defn advance
  [state observation timestamp]
  (let [next-step (state-machine/step (:step state) observation timestamp)
        history (historian/augment-step-history (:history state) next-step)]
    {:step next-step
     :history history}))

;; Routing

(defroutes app
  (GET "/" [] html/index)
  (GET "/current-picture-url" []
       (let [observation (collectors/collect)
             state (swap! state-atom advance observation (Instant/now))]
         (-> state :step :current-picture)))
  (GET "/history" []
       (let [state @state-atom]
         (html/history
          (:step state)
          (historian/explain-step-history (:history state)))))
  (route/not-found "Page not found..."))

;; Handler

(def handler
  (-> app
      (wrap-resource "public")))
