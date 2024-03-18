(ns foodapp.events
  (:require
 ;  [re-frame.core :as re-frame ]
   [re-frame.core :refer [reg-event-db reg-event-fx inject-cofx]]
   [foodapp.db :as db]
   [ajax.core :as ajax]    ;; so you can use this in the response-format below
   ))

(reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))


(reg-event-fx :log/msg [(inject-cofx :log)]
              (fn [cofx [_ msg]]
                (.info js/console "-- reg-event-fx ---") 
               ; (.info js/console "evt: " evt)
                (.info js/console "msg: " msg)
                (.info js/console "log current value: " (:log cofx))
                {:log msg}   ;; side efects map
                )
              )

;; default handler from example
(reg-event-fx                             ;; note the trailing -fx
  :handler-with-http                      ;; usage:  (dispatch [:handler-with-http])
  (fn [{:keys [db]} _]                    ;; the first param will be "world"
    {:db   (assoc db :show-twirly true)   ;; causes the twirly-waiting-dialog to show??
     :http-xhrio {:method          :get
                  :uri             "https://api.github.com/orgs/day8"
                  :timeout         8000                                           ;; optional see API docs
                  :response-format (ajax/json-response-format {:keywords? true})  ;; IMPORTANT!: You must provide this.
                  :on-success      [::good-http-result]
                  :on-failure      [::bad-http-result]}}))


(reg-event-fx                             ;; note the trailing -fx
  :http-get                     ;; usage:  (dispatch [:http-get])
  (fn [{:keys [db]} [_ uri query-params]]                    ;; the first param will be "world"
    {:db   (assoc db :show-twirly false)   ;; causes the twirly-waiting-dialog to show??
     :http-xhrio {:method          :get
                  :uri             uri
                  :params          query-params
                  :timeout         8000                                           ;; optional see API docs
                  :response-format (ajax/json-response-format {:keywords? true})  ;; IMPORTANT!: You must provide this.
                  :on-success      [::good-http-result]
                  :on-failure      [::bad-http-result]}}))



(reg-event-db
  ::good-http-result
  (fn [db [_ result]]
    (.info js/console "good http result: " result)
    (assoc db :success-http-result result)
    ; db
    )
 )


(reg-event-db
  ::bad-http-result
  (fn [db [_ result]]
    ;; result is a map containing details of the failure   
      (.info js/console "bad http result: " result)
  ;  db
     (assoc db :failure-http-result result)
    )
 )