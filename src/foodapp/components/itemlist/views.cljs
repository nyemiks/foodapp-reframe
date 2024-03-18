(ns foodapp.components.itemlist.views
  (:require
            [foodapp.components.item.views :refer [item]])
  )


(defn itemlist [food]
    [:div 
       (map (fn [record]
                   ^{:key (:id record)}   [item record]
                   ) 
                 (:extendedIngredients food)
                 )
     ]
  )