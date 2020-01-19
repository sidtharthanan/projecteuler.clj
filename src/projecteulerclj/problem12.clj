(ns projecteulerclj.problem12
  (:require [projecteulerclj.test-suite :refer :all]
            [projecteulerclj.lib :refer [smallest-divisor]]))

;(defn triangles1
;  ([] (triangles1 1 1))
;  ([n t] (cons t (lazy-seq (triangles1 (inc n) (+' n t 1))))))

(def triangles (map #(*' % (inc %) 1/2) (iterate inc 1)))

(defn divisors [n]
  (loop [divisor 1N
         factors #{}]
    (if (<= divisor (Math/ceil (Math/sqrt n)))
      (recur (inc divisor)
        (apply conj factors
               (if (zero? (rem n divisor)) [divisor (quot n divisor)])))
      factors)))

(defn main [threshold]
  (some #(and (<= threshold (count (divisors %))) %) triangles))

(defn problem12 []
  (exec-v2 "problem12"
           "What is the value of the first triangle number to have over <NUMBER> divisors?"
           main
           {:scene "Over 3 Divisors" :in [3] :out 6}
           {:scene "Over 4 Divisors" :in [4] :out 6}
           {:scene "Over 5 Divisors" :in [5] :out 28}
           {:scene "Over 6 Divisors" :in [6] :out 28}
           {:scene "Over 7 Divisors" :in [7] :out 36}
           {:scene "Over 8 Divisors" :in [8] :out 36}
           {:scene "Over 9 Divisors" :in [9] :out 36}
           {:scene "Over 10 Divisors" :in [10] :out 120}
           {:scene "Over 11 Divisors" :in [11] :out 120}
           {:scene "Over 12 Divisors" :in [12] :out 120}
           {:scene "Over 16 Divisors" :in [16] :out 120}
           {:scene "Over 500 Divisors" :in [500] :out 76576500}))
