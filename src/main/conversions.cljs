(ns conversions)

(def oz-in-butter 4)
(def oz-in-lb 16)
(def oz-in-kg 35.26)
(def g-in-oz 28.35)

(defn to-oz [weight unit]
  (* weight
     (if (= unit "lbs")
       oz-in-lb
       oz-in-kg)))

(defn oz-to-g [ounces]
  (* ounces g-in-oz))

(defn oz-to-butter [ounces]
  (/ ounces oz-in-butter))

(defn to-g [weight units]
  (->> (to-oz weight units)
       (oz-to-g)))

(defn to-butter [weight units]
  (->> (to-oz weight units)
       (oz-to-butter)))