(ns foodapp.components.fooddetail.views
   (:require 
      [re-frame.core :as re-frame :refer [subscribe dispatch]]
      [foodapp.components.fooddetail.subs :as food-subs]
    ;  [foodapp.components.fooddetail.events :as events]
      [foodapp.components.itemlist.views :refer [itemlist]]
      [clojure.string :as str]
            )
  )

; write url here
; https://api.spoonacular.com/recipes/658295/information
;(def api-key "391159f53197412890f034c2971782f2")
;   391159f53197412890f034c2971782f2  (romanisitua@gmail.com)         a6d0a381062045f58e69882015194e3f (autisi@yahoo.com)
;   94a1f0c9935a4ca39c218b8abe9afd0f  (nyemike2000@gmail.com)
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


(defn food-detail []
  (let [
          id  @(subscribe [::food-subs/food-id])
          food @(subscribe [::food-subs/food])
        ]
      
     (.info js/console "selected food id: " id)   
    
    ; 20240315 not good practice dispatch should be done in event handling
   ; (when id       (dispatch [::events/get-recipe-info (get-url id) query-params])       )
     
     [:div 
       
      [:div.recipeCard 
           [:h1.recipeName (:title food)]
       

           [:img.recipeImage {:src (:image food)}]
            
            [:div.recipeDetails 
              [:span [:strong (:readyInMinutes food) " Minutes"] ]
              [:span [:strong " Serves: " (:servings food)] ]
              [:span  [:strong (if (:vegetarian food) " vegetarian" " non - vegetarian")]]
              [:span [:strong (if (:vegen food) " vegan" "")]]
             ]
       

            [:div "$" [:span [:strong (/ (:pricePerServing food) 100) " Per Serving"]]]
        
       ] 
      
           [:h2 "Ingredients"] 
      
            [itemlist food]
           

          [:h2 "Instructions"] 
      
       [:div.recipeInstructions 
         
          [:ol 
              (map (fn [item]                   
                     (map  (fn [step]
                             [:li {:key (:number step)} (:step step)]
                             ) (:steps item))

                     ) (:analyzedInstructions food))
           ]
        
        ]
      ]
      
    )
 
  )