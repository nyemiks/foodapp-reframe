(ns foodapp.components.foodlist.views
 (:require 
  [re-frame.core :as re-frame :refer [subscribe]]
   [foodapp.components.foodlist.subs :as foodlist-subs]
   [foodapp.components.fooditem.views :refer [food-item]]
           ) 
  )


(defn food-list []
  (let [foodData @(subscribe [::foodlist-subs/food-data])]
      [:div 
     (map (fn [item] 
        ;  [:h1 {:key (:id item)} (:title item)]
          ^{:key (:id item)} [food-item item]
          ) 
        foodData)
     ]
    )
  )