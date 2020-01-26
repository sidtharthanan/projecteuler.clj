(ns projecteulerclj.problem23
  (:require [projecteulerclj.test-suite :refer :all]
            [projecteulerclj.lib :refer [pdivisors-seq]]))

(defn main [limit]
  (let [abundant?       (fn [[n divs]] (< n (reduce + divs)))
        abundant-sums   (fn [a-seq]
                          (for [n1     a-seq
                                n2     a-seq
                                :while (<= n2 (- limit n1))]
                            (+ n1 n2)))
        sum-of-pos-ints (* limit (inc limit) 1/2)]
    (->> pdivisors-seq
         (filter abundant?)
         (map first)
         (take limit)
         (abundant-sums)
         (filter #(<= % limit))
         (set)
         (reduce +)
         (- sum-of-pos-ints))))

(defn problem23 []
  (exec-v2 "Problem23"
           "Non-abundant sums"
           main
           {:in [10] :out 55}
           {:in [24] :out 276}
           {:in [10000] :out 3731004}
           {:in [28123] :out 4179871}))
