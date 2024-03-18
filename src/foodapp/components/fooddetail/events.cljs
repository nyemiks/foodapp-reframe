(ns foodapp.components.fooddetail.events
  (:require 
   [re-frame.core :refer [reg-event-db reg-event-fx debug]]
   [ajax.core :as ajax]    ;; so you can use this in the response-format below
   )
  )


(reg-event-fx                             ;; note the trailing -fx
  ::get-recipe-info   [debug]                  ;; usage:  (dispatch [::events/search-recipe])
  (fn [{:keys [db]} [_ uri query-params]]                    ;; the first param will be "world"
    (.info js/console "get recipe info uri: " uri " query params: " query-params)
    {:db   (assoc db :show-twirly false)   ;; causes the twirly-waiting-dialog to show??
     :http-xhrio {
                  :method          :get
                  :uri             uri
                  :params          query-params
                  :timeout         8000                                           ;; optional see API docs
                  :response-format (ajax/json-response-format {:keywords? true})  ;; IMPORTANT!: You must provide this.
                  :on-success      [::fetch-recipe-success]
                  :on-failure      [::fetch-recipe-failure]
                  }
     }
    )
 )



(reg-event-db
  ::fetch-recipe-success
  (fn [db [_ result]]
    (.info js/console "search results: " result)
    (assoc db :recipe-info result)
   
    )
 )


(reg-event-db
  ::fetch-recipe-failure
  (fn [db [_ result]]
    ;; result is a map containing details of the failure   
      (.info js/console "search failed: " result)  
     (assoc db :recipe-info-error result :food-id  nil)
    )
 )