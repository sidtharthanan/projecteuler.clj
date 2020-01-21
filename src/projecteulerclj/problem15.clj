(ns projecteulerclj.problem15
  (:require [projecteulerclj.test-suite :refer :all]))

;so slow
(defn per-comb [n r]
  (if
    (= r 2)
    (* n (inc n) 1/2)
    (reduce +' (map #(per-comb % (dec r)) (range n 0 -1)))))

;so slow
(defn main1
  ([size] (per-comb (inc size) size))
  ([rows cols] (per-comb (inc rows) cols)))

(defn fact [n]
  (loop [p 1N
         n n]
    (if (zero? n) p (recur (* p n) (dec n)))))

(defn main
  ([size] (main size size))
  ([rows cols]
   (/ (fact (+' rows cols)) (fact cols) (fact rows))))

(defn problem15 []
  (exec-v2 "problem15"
           "Starting in the top left corner of a 2×2 grid,
              and only being able to move to the right and down,
              there are exactly 6 routes to the bottom right corner."
           main
           {:scene "for 1X1" :in [1 1] :out 2}
           {:scene "for 1X2" :in [1 2] :out 3}
           {:scene "for 1X3" :in [1 3] :out 4}
           {:scene "for 2X1" :in [2 1] :out 3}
           {:scene "for 2X2" :in [2 2] :out 6}
           {:scene "for 2X3" :in [2 3] :out 10}
           {:scene "for 3X1" :in [3 1] :out 4}
           {:scene "for 3X2" :in [3 2] :out 10}
           {:scene "for 3X3" :in [3 3] :out 20}
           {:scene "for 4X4" :in [4] :out 70}
           {:scene "for 5X5" :in [5] :out 252}
           {:scene "for 6X6" :in [6] :out 924}
           {:scene "for 10X10" :in [10] :out 184756}
           {:scene "for 20X20" :in [20] :out 137846528820}))
