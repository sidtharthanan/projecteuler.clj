(ns projecteulerclj.problem16
  (:require [projecteulerclj.test-suite :refer :all]))

(defn main [b p]
  (reduce + (map #(Integer. (str %)) (str (.pow (biginteger b) p)))))

(defn problem16 []
  (exec-v2 "problem16"
           "What is the sum of the digits of the number 2 ^ 1000?"
           main
           {:scene "for 1^1" :in [1 1] :out 1}
           {:scene "for 1^2" :in [1 2] :out 1}
           {:scene "for 2^1" :in [2 1] :out 2}
           {:scene "for 2^2" :in [2 2] :out 4}
           {:scene "for 2^3" :in [2 3] :out 8}
           {:scene "for 2^4" :in [2 4] :out 7}
           {:scene "for 2^5" :in [2 5] :out 5}
           {:scene "for 2^6" :in [2 6] :out 10}
           {:scene "for 2^6" :in [2 7] :out 11}
           {:scene "for 2^6" :in [2 8] :out 13}
           {:scene "for 2^10" :in [2 10] :out 7}
           {:scene "for 2^100" :in [2 100] :out 115}
           {:scene "for 2^1000" :in [2 1000] :out 1366}))

