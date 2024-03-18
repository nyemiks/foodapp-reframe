(ns foodapp.views
  (:require
   [re-frame.core :as re-frame :refer [subscribe]]
   [foodapp.subs :as subs]
   [foodapp.components.search.views :refer [search]] 
  ; [foodapp.search.subs :as search-subs]  
   [foodapp.components.nav.views :refer [nav]]
   [foodapp.components.container.views :refer [container]]
   )
  )



(defn app []
   [:div 
        [nav]
        [search]       
        [container]
      ]
  )


(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
   ; [:div [:h1 "Hello from " @name] ]
       [app]
    )
  )
