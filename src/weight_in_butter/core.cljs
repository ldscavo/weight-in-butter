(ns weight-in-butter.core)

(defn to-oz [weight unit]
  (* weight
    (if (= unit "lbs")
      16
      35.26)))

(defn oz-to-butter [ounces]
  (/ ounces 4))

(defn to-butter [weight unit]
  (oz-to-butter
    (to-oz weight unit)))

(defn output [content]
  (set!
    (.-innerHTML
      (.getElementById js/document "results"))
    content))

(defn get-input-value [id]
  (.-value
    (.getElementById js/document id)))

(defn weight []
  (int
    (get-input-value "weight")))

(defn unit [] 
  (get-input-value "units"))

(defn calculate []
  (output
    (str "This weighs as much as "
      (to-butter (weight) (unit))
      " sticks of butter!")))