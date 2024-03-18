(ns foodapp.components.item.views
  (:require  [clojure.string :as str])
  )


(defn item [record]
   [:div 
    [:div.itemContainer2   
     [:div.imageContainer [:img {                               
                                :src (str/join "" ["https://spoonacular.com/cdn/ingredients_100x100/" (:image record)])
                                } 
                          ]
      ]                      
     [:div.nameContainer 
          [:div.name (:name record)] 
          [:div.amount (:amount record) " " (:unit record)]
      ]
     ]
    ]
  )