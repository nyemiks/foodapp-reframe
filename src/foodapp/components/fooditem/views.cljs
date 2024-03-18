(ns foodapp.components.fooditem.views
  (:require [foodapp.components.fooditem.events :as events]
            [re-frame.core :refer [dispatch]]
              [clojure.string :as str]
            )
  )


(def query-params {:apiKey "94a1f0c9935a4ca39c218b8abe9afd0f"})



(defn- get-url[food-id]
  (let 
        [
          url1 "https://api.spoonacular.com/recipes/"
          url2   "/information"
          final-url (str/join "" [url1 food-id url2])
        ]
        (.info js/console "url: " final-url)
        final-url
    )
 
  )


(defn- select-id [_ item]
      (let  [
             id (:id item) 
             ] 
        
       ;  (dispatch [::events/update-food-id id])    
          (dispatch [::events/get-food-detail id (get-url id) query-params])                                   
        )
  )


(defn food-item [item]
  [:div.itemContainer 
   [:img.itemImage {:src (:image item)}]
   [:div.itemContent
       [:p.itemName (:title item)]
    ]
  
   [:div.buttonContainer 
      [:button.itemButton {
                           :on-click #(select-id %1 item)
                           } 
       "View Recipe"
       ]
    ]
   
   ]
  
  )