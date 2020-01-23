(ns projecteulerclj.problem67
  (:require [projecteulerclj.test-suite :refer :all]
            [projecteulerclj.problem18 :as problem18]))

(def data (slurp "src/projecteulerclj/data/67.txt"))

(defn problem67 []
  (exec-v2 "problem67"
           "Maximum path sum I. Finding the best route on a triangle."
           problem18/main
           {:scene "Big trainagle from text file" :in [data] :out 1074}))
