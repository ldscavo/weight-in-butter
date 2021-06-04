(ns app
  (:require [interface :as ui]))

(defn init []
  (ui/bind-calculation)
  (ui/bind-gram-display)
  (ui/display-grams))