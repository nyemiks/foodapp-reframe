(ns foodapp.components.container.views
  (:require 
   [foodapp.components.foodlist.views :refer [food-list]]
   [foodapp.components.innercontainer.views :refer [inner-container]]
   [foodapp.components.fooddetail.views :refer [food-detail]]
                
  )
)

(defn container []
  [:div.parentContainer
      [inner-container [food-list]]
      [inner-container [food-detail]]
   ]
  )