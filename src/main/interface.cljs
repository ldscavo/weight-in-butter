(ns interface
  (:require [conversions :as conv]))

(defn output [id content]
  (set!
   (.-innerHTML
    (.getElementById js/document id))
   content))

(defn get-input-value [id]
  (.-value
   (.getElementById js/document id)))

(defn get-weight []
  (int
   (get-input-value "weight")))

(defn get-units []
  (get-input-value "units"))

(defn get-grams []
  (int (conv/to-g (get-weight) (get-units))))

(defn get-butter []
  (conv/to-butter (get-weight) (get-units)))

(defn display-grams []
  (let [grams (get-grams)]
    (output "grams"
      (str "(" grams " grams)"))))

(defn calculate []
  (let [butter (get-butter)]
    (output "results"
      (str "This weighs as much as " butter " sticks of butter!"))))

(defn bind-gram-display []
  (.addEventListener
   (.getElementById js/document "weight")
   "keyup"
   display-grams))

(defn bind-calculation []
  (.addEventListener
   (.getElementById js/document "calculate")
   "click"
   calculate))