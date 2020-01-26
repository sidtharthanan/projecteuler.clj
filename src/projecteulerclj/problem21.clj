(ns projecteulerclj.problem21
  (:require [projecteulerclj.test-suite :refer :all]
            [projecteulerclj.lib :refer [pdivisors-seq]]))

(def p-divs-sum-seq
  "Seq of numbers with sum of proper divisors"
  (map (fn [[n divs]] [n (reduce + divs)]) pdivisors-seq))

(defn main [limit]
  (let [amicable?     (fn [[[n n-sum] [m m-sum]]]
                        (and (not= m n) (= m-sum n) (= n-sum m)))
        amicable-pair (fn [[n n-sum]]
                        [[n n-sum] (nth p-divs-sum-seq (dec n-sum))])]
    (->> p-divs-sum-seq
         (take limit)
         (rest)
         (map amicable-pair)
         (filter amicable?)
         (map (comp first first))
         (reduce +))))

(defn problem21 []
  (exec-v2 "problem21"
           "Sum of all the amicable numbers under <NUMBER n>"
           main
           {:in [250] :out 220}
           {:in [300] :out 504}
           {:in [10000] :out 31626}))
