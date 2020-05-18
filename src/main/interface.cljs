(ns interface
  (:require [conversions :as conv]))

(defn output [content]
  (set!
   (.-innerHTML
    (.getElementById js/document "results"))
   content))

(defn get-input-value [id]
  (.-value
   (.getElementById js/document id)))

(defn get-weight []
  (int
   (get-input-value "weight")))

(defn get-units []
  (get-input-value "units"))

(defn calculate []
  (output
   (str "This weighs as much as "
        (conv/to-butter (get-weight) (get-units))
        " sticks of butter!")))

(defn bind-calculation []
  (.addEventListener
   (.getElementById js/document "calculate")
   "click"
   calculate))