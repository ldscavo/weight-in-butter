(ns conversions)

(def oz-in-butter 4)
(def oz-in-lb 16)
(def oz-in-kg 35.26)
(def cups-in-butter 0.5)
(def tbsp-in-butter 16)

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

(defn oz-to-butter [ounces]
  (/ ounces oz-in-butter))

(defn to-butter [weight units]
  (->> (to-oz weight units)
       (oz-to-butter)))

(defn butter-to-cups [butter]
  (* butter cups-in-butter))

(defn butter-to-tbsp [butter]
  (* butter tbsp-in-butter))

(comment
  (get-operand "lbs")
  (get-operand "kg")
  (to-butter 0.35 "lbs")
  (butter-to-cups 1)
  (butter-to-cups 0.69)
  (butter-to-tbsp 1))