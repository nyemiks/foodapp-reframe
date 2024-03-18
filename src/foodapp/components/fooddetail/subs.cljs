(ns foodapp.components.fooddetail.subs
   (:require 
   [re-frame.core :as re-frame :refer [reg-sub]]
            )
  )



(reg-sub ::food-id
         (fn [db _] 
           (:food-id db)
           )
         )


(reg-sub ::food
         (fn [db _]
           (:recipe-info db)
           )
         )