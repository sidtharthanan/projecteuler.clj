(ns projecteulerclj.problem28
  (:require [projecteulerclj.test-suite :refer :all]))

(defn main [size]
  (if (= size 1)
    1
    (+' (main (- size 2))
        (*' (- size 2) (- size 2))
        (*' (dec size) (dec size))
        (*' 2 size size)
        1)))

(defn problem28 []
  (exec-v2 "problem28"
           "Number spiral diagonals"
           main
           {:in [1] :out 1}
           {:in [3] :out 25}
           {:in [5] :out 101}
           {:in [1001] :out 669171001}))
