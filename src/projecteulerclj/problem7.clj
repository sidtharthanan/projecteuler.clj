(ns projecteulerclj.problem7
  (:require [projecteulerclj.test-suite :as tests]
            [projecteulerclj.lib :refer [primes]]))

(defn main [n]
  (if (pos? n) (nth (primes) (dec n)) "Need a positive number"))

(defn problem7 []
  (tests/exec "problem7" "nth prime" main
              [[1] 2]
              [[2] 3]
              [[3] 5]
              [[10] 29]
              [[100] 541]
              [[1000] 7919]
              [[10001] 104743]))
