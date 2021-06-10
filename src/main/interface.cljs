(ns interface
  (:require [conversions :as conv]))

(def log (.-log js/console))

(defn set-result-field [id content]
  (-> js/document
      (.getElementById id)
      (.-innerHTML)
      (set! content)))

(defn get-input-value [id]
  (-> js/document
      (.getElementById id)
      (.-value)))

(defn show-or-hide [id display]
  (-> js/document
      (.getElementById id)
      (.-style)
      (.-display)
      (set! (if (= display :show) "block" "none"))))

(defn show-after-timer [id ms]  
  (js/setTimeout #(show-or-hide id :show) ms))

(defn get-weight []
  (-> (get-input-value "weight") float))

(defn get-units []
  (get-input-value "units"))

(defn get-butter []
  (conv/to-butter (get-weight) (get-units)))

(defn get-results [weight units]
  (let [butter (conv/to-butter weight units)
        cups (conv/butter-to-cups butter)
        tbsp (conv/butter-to-tbsp butter)]
    {:weight weight
     :units units
     :butter (-> butter int)
     :cups (-> cups (.toFixed 1))
     :tbsp (-> tbsp int)}))

(defn update-table-data [results]
  (set-result-field "weight-display" (:weight results))
  (set-result-field "units-display" (:units results))
  (set-result-field "butter-sticks" (:butter results))
  (set-result-field "cups" (:cups results))
  (set-result-field "tbsp" (:tbsp results)))

(defn render-results []
  (show-or-hide "weights" :hide)
  (show-or-hide "warning" :hide)
  (let [weight (get-weight)
        units (get-units)
        results (get-results weight units)]
    #_(log results)
    (update-table-data results)
    (show-after-timer "weights" 50)
    (when (> (:butter results) 1)
      (show-after-timer "warning" 2000))))

(defn bind-element [id event f]
  (-> js/document
      (.getElementById id)
      (.addEventListener event f)))

(defn bind-calculation []
  (bind-element "calculate" "click" render-results))