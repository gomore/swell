(ns swell.html
  (:require [hiccup.core :refer (html)]
            [clojure.pprint :refer (pprint)]))

(def index
  (html
   [:head
    [:link {:rel "stylesheet", :type "text/css", :href "app.css"}]]
   [:body
    [:div.fill-screen
     [:img#picture.fit]]
    [:script {:src "app.js"}]]))

(defn history
  [current-step explained-history]
  (html
   [:body
    [:h1 "History"]
    [:img {:src (-> current-step :current-picture)}]
    [:pre (with-out-str (-> current-step :observation pprint))]
    [:table
     [:tr [:th] [:th "added"] [:th "removed"] [:th]]
     (for [{:keys [step added removed]} explained-history]
       [:tr
        [:td (:timestamp step)]
        [:td [:pre (if (nil? added) "-" (with-out-str (pprint added)))]]
        [:td [:pre (if (nil? added) "-" (with-out-str (pprint removed)))]]
        [:td [:img {:src (:current-picture step), :width 100}]]])]]))
