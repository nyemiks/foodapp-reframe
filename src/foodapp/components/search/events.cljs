(ns foodapp.components.search.events
  (:require  
   [re-frame.core :refer [reg-event-db reg-event-fx]]
   [ajax.core :as ajax]    ;; so you can use this in the response-format below
      )
  )



(reg-event-fx                             ;; note the trailing -fx
  ::search-recipe                     ;; usage:  (dispatch [::events/search-recipe])
  (fn [{:keys [db]} [_ uri query-params]]                    ;; the first param will be "world"
    {:db   (assoc db :show-twirly false)   ;; causes the twirly-waiting-dialog to show??
     :http-xhrio {:method          :get
                  :uri             uri
                  :params          query-params
                  :timeout         8000                                           ;; optional see API docs
                  :response-format (ajax/json-response-format {:keywords? true})  ;; IMPORTANT!: You must provide this.
                  :on-success      [::search-success]
                  :on-failure      [::search-failed]}}))



(reg-event-db
  ::search-success
  (fn [db [_ result]]
  ;  (.info js/console "search results: " result)
    (assoc db :food-data (:results result))
   
    )
 )


(reg-event-db
  ::search-failed
  (fn [db [_ result]]
    ;; result is a map containing details of the failure   
      (.info js/console "search failed: " result)  
     (assoc db :search-failure result)
    )
 )