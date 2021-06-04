(ns conversions)

(def oz-in-butter 4)
(def oz-in-lb 16)
(def oz-in-kg 35.26)
(def g-in-oz 28.35)
(def g-in-lb 453.592)
(def g-in-kg 1000)

(defn validate [n]
  (if (< n 0) 0 n))

(defn get-operand [unit]
  (if (= unit "lbs")
    oz-in-lb
    oz-in-kg))

(defn to-oz [weight unit]
  (->> (get-operand unit)
       (* weight)
       (validate)))

(defn oz-to-g [ounces]
  (->> g-in-oz
       (* ounces)
       (validate)))

(defn oz-to-butter [ounces]
  (/ ounces oz-in-butter))

(defn to-g [weight units]
  (if (= units "lbs")
    (* weight g-in-lb)
    (* weight g-in-kg)))

(defn to-butter [weight units]
  (->> (to-oz weight units)
       (oz-to-butter)))

(comment
  (to-g 50 "kg"))