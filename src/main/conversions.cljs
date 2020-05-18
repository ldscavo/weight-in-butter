(ns conversions)

(def oz-in-butter 4)
(def oz-in-lb 16)
(def oz-in-kg 35.26)

(defn to-oz [weight unit]
  (* weight
     (if (= unit "lbs")
       oz-in-lb
       oz-in-kg)))

(defn oz-to-butter [ounces]
  (/ ounces oz-in-butter))

(defn to-butter [weight unit]
  (oz-to-butter
   (to-oz weight unit)))