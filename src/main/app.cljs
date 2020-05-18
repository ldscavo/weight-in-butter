(ns app
  (:require [interface :as ui]))

(defn init []
  (ui/bind-calculation))