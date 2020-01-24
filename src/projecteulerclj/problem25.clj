(ns projecteulerclj.problem25
  (:require [projecteulerclj.test-suite :refer :all]
            [projecteulerclj.lib :refer [fibo-seq]]))

(defn main [limit]
  (->> (fibo-seq 1N 1N)
       (map vector (rest (range)))
       (filter #(<= limit (count (str (second %)))))
       (first)
       (first)))

(defn problem25 []
  (exec-v2 "problem25"
           "Find the index of first Fibonacci number to have <n NUMBER> of digits"
           main
           {:in [2] :out 7}
           {:in [3] :out 12}
           {:in [4] :out 17}
           {:in [100] :out 476}
           {:in [1000] :out 4782}))
