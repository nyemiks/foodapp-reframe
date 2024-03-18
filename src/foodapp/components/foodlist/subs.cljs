(ns foodapp.components.foodlist.subs
  (:require 
   [re-frame.core :as re-frame :refer [reg-sub]]
            )
  )


(reg-sub ::food-data 
         (fn [db _] 
           (:food-data db)
           )
         )