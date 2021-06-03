(ns interface
  (:require [conversions :as conv]))

(defn output [id content]
  (-> js/document
      (.getElementById id)
      (.-innerHTML)
      (set! content)))

(defn get-input-value [id]
  (-> js/document
      (.getElementById id)
      (.-value)))

(defn get-weight []
  (-> (get-input-value "weight") int))

(defn get-units []
  (get-input-value "units"))

(defn get-grams []
  (-> (conv/to-g (get-weight) (get-units)) int))

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

(defn bind-element [id event f]
  (-> js/document
      (.getElementById id)
      (.addEventListener event f)))

(defn bind-gram-display []
  (bind-element "weight" "keyup" display-grams))

(defn bind-calculation []
  (bind-element "calculate" "click" calculate))