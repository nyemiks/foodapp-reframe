(ns foodapp.console.logger
  (:require [re-frame.core :refer [reg-fx reg-cofx]]
            )
  )

; side effect function
(defn log [msg]
  (.info js/console "-- log ---")
   (.info js/console msg)
   ; (.alert js/console msg)
  )

; register effect handler. invokes side effect
(reg-fx :log (fn [message]
               (.info js/console "-- reg-fx ---")
               (log message)
               )
        )


; register co effect handler so it's available in the coeffect map
(reg-cofx :log (fn [cofx _]
                     (assoc cofx :log "hello world !!!")
                     )
          )