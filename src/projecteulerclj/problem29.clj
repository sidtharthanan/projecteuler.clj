(ns projecteulerclj.problem29
  (:require [projecteulerclj.test-suite :refer :all]))

(defn main [limit]
  (count
   (set
     (for [x (range 2 (inc limit))
           y (range 2 (inc limit))]
       (.pow (biginteger x) y)))))

(defn problem29 []
  (exec-v2 "problem29"
           ""
           main
           {:in [5] :out 15}
           {:in [100] :out 9183}))
