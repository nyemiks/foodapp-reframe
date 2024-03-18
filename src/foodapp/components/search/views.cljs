(ns foodapp.components.search.views
  (:require [reagent.core :refer [atom]]
            [re-frame.core :refer [dispatch]]
            [foodapp.components.search.events :as events]
            )
  )

; example url https://api.spoonacular.com/recipes/complexSearch?query=pizza&apiKey=391159f53197412890f034c2971782f2
;   391159f53197412890f034c2971782f2  (romanisitua@gmail.com)         a6d0a381062045f58e69882015194e3f (autisi@yahoo.com)
; ;   94a1f0c9935a4ca39c218b8abe9afd0f  (nyemike2000@gmail.com)
(def uri "https://api.spoonacular.com/recipes/complexSearch")

(def query-params {:apiKey "94a1f0c9935a4ca39c218b8abe9afd0f"})


(defn- add-query [query]
  (.info js/console " add query: " query)
  (assoc query-params :query query)
  )

(defn search []
  (let [query (atom "")]
  ;  (.info js/console "Demo function executed! ")
   ; (dispatch [:log/msg "Demo function executed !"])
    (fn []
      [:div.searchContainer
          [:input.input {
                   :type "text"
                   :value @query
                   :on-change (fn [e]
                              (let 
                               [final-params (add-query (reset! query (.. e -target -value)))]
                                ;(dispatch [:http-get uri final-params])
                                (dispatch [::events/search-recipe uri final-params])
                                )                                
                                )
                   } 
           ]
   ]
      )
    )
  
  )