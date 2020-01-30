(ns projecteulerclj.problem27
  (:require [projecteulerclj.test-suite :refer :all]
            [projecteulerclj.lib :refer [prime?]]))

;(def prime-seq (primes))

;(defn prime? [n] (some #{n} (take-while #(<= % n) prime-seq)))

(defn quad-formula [x a b] (+' (*' x x) (*' a x) b))

(defn main [limit]
  (first
    (apply max-key second
           (for [a (range (- 1 limit) limit)
                 b (range (- limit) (inc limit))]
             [(*' a b) (count (take-while #(prime? (quad-formula % a b)) (range)))]))))

(defn problem27 []
  (exec-v2 "problem27"
           ""
           main
           {:in [10] :out -21}
           {:in [100] :out -1455}
           {:in [200] :out -4925}
           {:in [500] :out -18901}
           {:in [1000] :out -59231}))
