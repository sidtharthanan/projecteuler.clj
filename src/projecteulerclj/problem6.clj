(ns projecteulerclj.problem6
  (:require [projecteulerclj.test-suite :as tests]))

(def square #(* % %))

(defn main [range]
  (- (square (reduce + range)) (reduce + (map square range))))

(defn problem6 []
  (tests/exec "problem6" "range -> sum of squares - square of sum ->" main
              [[(range 1 11)] 2640]
              [[(range 1 6)] 170]
              [[(range 1 101)] 25164150]))
