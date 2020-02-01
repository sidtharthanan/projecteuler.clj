(ns projecteulerclj.problem30
  (:require [projecteulerclj.test-suite :refer :all]
            [clojure.string :as str]))

(defn get-limit [p]
  (loop [digits 1]
    (if (<= digits (count (str (bigint (* digits (Math/pow 9 p))))))
      (recur (inc digits))
      (dec digits))))

(defn main [power]
  (defn digit-pow-sum? [n]
    (== n
        (loop [sum 0
               n   n]
          (if (< 0 n) (recur (+ sum (Math/pow (rem n 10) power)) (quot n 10)) sum))))
  (reduce + (filter digit-pow-sum? (range 2 (Math/pow 10 (get-limit power))))))

(defn problem30 []
  (exec-v2 "problem30 unit tests" "finding max limit for power p"
           get-limit
           {:in [3] :out 4}
           {:in [4] :out 5}
           {:in [5] :out 6})
  (exec-v2 "problem30 main"
           "Digit fifth powers"
           main
           {:in [3] :out 1301}
           {:in [4] :out 19316}
           {:in [5] :out 443839}))
