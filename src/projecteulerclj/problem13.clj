(ns projecteulerclj.problem13
  (:require [projecteulerclj.test-suite :refer :all]))

(defn main [ds]
  (subs
   (str
    (with-open [rdr (clojure.java.io/reader "src/projecteulerclj/data/13.txt")]
      (reduce #(+' %1 (BigInteger. %2)) 0 (line-seq rdr))))
   0 ds))

(defn problem13 []
  (exec-v2 "problem13"
           "First n digits of the sum of 100 50 digit numbers"
           main
           {:scene "Sum" :in [10] :out "5537376230"}))
