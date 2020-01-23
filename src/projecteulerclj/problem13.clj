(ns projecteulerclj.problem13
  (:require [projecteulerclj.test-suite :refer :all]
            [clojure.string :refer [split-lines]]))

(defn main [digits]
  (subs
   (str
    (reduce +'
            (map #(BigInteger. %)
                 (split-lines (slurp "src/projecteulerclj/data/13.txt")))))
   0 digits))

(defn problem13 []
  (exec-v2 "problem13"
           "First <NUMBER n> digits of the sum of 100 50 digit numbers"
           main
           {:scene "Sum" :in [10] :out "5537376230"}))
