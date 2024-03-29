(ns foodapp.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [foodapp.events :as events]
   [foodapp.views :as views]
   [foodapp.config :as config]   
   [foodapp.console.logger :refer [log]]
   [day8.re-frame.http-fx]   ;; <-- add this for initialization purposes
   [re-frisk.core :refer [enable-re-frisk!]]  ;; initialize refrisk
   ))


(defn dev-setup []
  (when config/debug?
    (enable-re-frisk!)
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
