(ns projecteulerclj.problem9
  (:require [projecteulerclj.lib :refer []]
            [projecteulerclj.test-suite :refer :all]))

(defn main [sum]
  (first (for [a (range 1 sum) b (range 1 sum)
               :let [c (- sum a b)]
               :when (and (< a b c) (= (+ (* a a) (* b b)) (* c c)))]
           (* a b c))))

(defn problem9 []
  (exec-v2 "Pythagorean triplet"
           "For any {a,b,c}, a<b<c and a^2+b^2=c^2 and a+b+c=INPUT, find a*b*c."
           main
           {:scene "Triplet {3,4,5} :in 12, :out 60" :in [12] :out 60}
           {:scene "triplet {200,375,425} :in 1000, :out 31875000" :in [1000] :out 31875000}))