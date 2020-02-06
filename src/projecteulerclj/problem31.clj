(ns projecteulerclj.problem31
  (:require [projecteulerclj.test-suite :refer :all]))

(def less-or-2 (map #(Math/floorDiv (+ % 2) 2) (rest (range))))

(defn reduce-seq
  "Sequence referring itself for creating items later in the sequence.
  (def factorial-seq (reduce-seq (fn [s n] (*' (nth s (dec n)) (inc n))) [1] (next (range))))
  (take 10 factorial-seq)
  ; (1 2 6 24 120 720 5040 40320 362880 3628800)
  "
  [f init coll]
  (let [f2 (fn f2 [acc [it & coll]]
             (if (next coll)
               (cons (f acc it) (lazy-seq (f2 (concat acc [(f acc it)]) coll)))
               (list (f acc it))))]
    (concat init (f2 init coll))))

(defn combo-matrix-for-value-less-or-n [nn smaller-matrix]
  (reduce-seq (fn [s n] (+ (nth s n) (nth smaller-matrix (+ n nn))))
              (concat (take (dec nn) smaller-matrix)
                      [(inc (nth smaller-matrix (dec nn)))])
              (range)))

(def combo-matrix
  (reduce
   (fn [smaller-matrices n]
     (concat smaller-matrices [(combo-matrix-for-value-less-or-n n (last smaller-matrices))]))
   [less-or-2]
   [5 10 20 50 100 200]))

;(println (apply map vector (map #(take 20 %) combo-matrix)))
;
; combinations of a sum possible with desired denominaiton and below
;  -  |    denominations
;sum  |  [1-2 5 10 20 50 £1 £2]
;1p   |  [1 1 1 1 1 1 1]
;2p   |  [2 2 2 2 2 2 2]
;3p   |  [2 2 2 2 2 2 2]
;4p   |  [3 3 3 3 3 3 3]
;5p   |  [3 4 4 4 4 4 4]
;6p   |  [4 5 5 5 5 5 5]
;7p   |  [4 6 6 6 6 6 6]
;8p   |  [5 7 7 7 7 7 7]
;9p   |  [5 8 8 8 8 8 8]
;10p  |  [6 10 11 11 11 11 11]
;11p  |  [6 11 12 12 12 12 12]
;12p  |  [7 13 15 15 15 15 15]
;13p  |  [7 14 16 16 16 16 16]
;14p  |  [8 16 19 19 19 19 19]
;15p  |  [8 18 22 22 22 22 22]
;16p  |  [9 20 25 25 25 25 25]
;17p  |  [9 22 28 28 28 28 28]
;18p  |  [10 24 31 31 31 31 31]
;19p  |  [10 26 34 34 34 34 34]
;20p  |  [11 29 40 41 41 41 41]

(defn main [total-pence]
  (nth (last combo-matrix) (dec total-pence)))

(defn problem31 []
  (exec-v2 "problem31 main"
           "Coin sums. Combinations"
           main
           {:in [200] :out 73682}
           {:in [500] :out 6295434}
           {:in [1000] :out 321335886}))
