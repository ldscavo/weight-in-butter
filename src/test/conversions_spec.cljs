(ns conversions-spec
  (:require [cljs.test :refer (deftest is)])
  (:require [conversions :as conv]))

(deftest validate-works-as-expected
  (is (= (conv/validate 5) 5))
  (is (= (conv/validate 0) 0))
  (is (= (conv/validate -1) 0)))

(deftest get-operand-works-as-expected
  (is (= (conv/get-operand "lbs") 16))
  (is (= (conv/get-operand "kg") 35.26)))

(deftest lb-to-oz-calculates-correctly
  (is (= (conv/to-oz 1 "lbs") 16))
  (is (= (conv/to-oz 5 "lbs") 80))
  (is (= (conv/to-oz 0 "lbs") 0))
  (is (= (conv/to-oz -1 "lbs") 0)))