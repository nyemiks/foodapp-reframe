(ns foodapp.components.fooditem.events
   (:require  
   [re-frame.core :refer [reg-event-db reg-event-fx]]
     [foodapp.components.fooddetail.events :as detail_events]
      )
  )


(reg-event-db
  ::update-food-id
  (fn [db [_ id]]
  ;  (.info js/console "update food id: " id)
    (assoc db :food-id id)   
    )
 )


(comment " get food detail. updates the food id then fetches the recipe info
          id url and query params should be passed
          ")
  
  (reg-event-fx
 ::get-food-detail
  (fn [cofx [_ id uri query-params]]
     (.info js/console "-- get food detail -- id: " id)
    {
     :db (assoc (:db cofx) :food-id id)  
     :dispatch [::detail_events/get-recipe-info uri query-params]
     }
    )
 )
  
  