(ns interface
  (:require [conversions :as conv]))

(defn set-result-field [id content]
  (-> js/document
      (.getElementById id)
      (.-innerHTML)
      (set! content)))

(defn get-input-value [id]
  (-> js/document
      (.getElementById id)
      (.-value)))

(defn show [id]
  (-> js/document
      (.getElementById id)
      (.-style)
      (.-display)
      (set! "block")))

(defn hide [id]
  (-> js/document
      (.getElementById id)
      (.-style)
      (.-display)
      (set! "none")))

(defn show-warning []  
  (js/setTimeout #(show "warning") 1200))

(defn hide-warning []
  (hide "warning"))

(defn get-weight []
  (-> (get-input-value "weight") int))

(defn get-units []
  (get-input-value "units"))

(defn get-grams []
  (-> (conv/to-g (get-weight) (get-units)) int))

(defn get-butter []
  (conv/to-butter (get-weight) (get-units)))

(defn display-grams []
  #_(let [grams (get-grams)]
    (if (> grams 0)
      (set-result-field "grams" (str grams "g"))
      (set-result-field "grams" ""))))

(defn calculate []
  (hide-warning)
  (let [butter (get-butter)]
    (set-result-field "results"
      (str "This weighs as much as " butter " sticks of butter"))
    (when (> butter 1)
      (show-warning))))

(defn bind-element [id event f]
  (-> js/document
      (.getElementById id)
      (.addEventListener event f)))

(defn bind-gram-display []
  (bind-element "weight" "keyup" display-grams)
  (bind-element "units" "change" display-grams))

(defn bind-calculation []
  (bind-element "calculate" "click" calculate))