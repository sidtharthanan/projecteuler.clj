(ns projecteulerclj.problem20
  (:require [projecteulerclj.test-suite :refer :all]
            [clojure.string :refer [split]]
            [projecteulerclj.lib :refer [factorial]]))

(defn main [n]
  (reduce + 0 (map bigint (split (str (factorial n)) #""))))

(defn problem20 []
  (exec-v2 "problem20"
           "Sum of digits in a factorial number"
           main
           {:in [10] :out 27}
           {:in [100] :out 648}))
