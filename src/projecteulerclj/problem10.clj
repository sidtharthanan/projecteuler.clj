(ns projecteulerclj.problem10
  (:require [projecteulerclj.lib :refer :all]
            [projecteulerclj.test-suite :refer :all]))

(defn main [max]
  (reduce + (primes max)))

(defn problem10 []
  (exec-v2 "problem10"
           "Find the sum of all the primes below LIMIT"
           main
           {:scene "primes {2,3,5,7} :in 10, :out 17" :in [10] :out 17}
           {:scene "primes {2,3,5,7.....} :in two million, :out 142913828922" :in [2000000] :out 142913828922}))