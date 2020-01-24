(ns projecteulerclj.problem21
  (:require [projecteulerclj.test-suite :refer :all]))

(def pdivisors-seq
  "Seq of numbers with sum of proper divisors"
  (let [seqfn
        (fn seqfn [prev n]
          (cons prev
                (let [max-div (Math/ceil (Math/sqrt n))]
                  (loop [div    2
                         divs   #{1}]
                    (if (<= div max-div)
                      (recur (inc div)
                        (apply conj divs
                               (if (zero? (rem n div))
                                 [div (quot n div)])))
                      (lazy-seq (seqfn [n (reduce + divs)] (inc n))))))))]
    (concat [[1] [2 1]] (seqfn [3 1] 4))))

(defn main [limit]
  (let [amicable?
        (fn [[n n-sum]]
          (let [[m m-sum] (nth pdivisors-seq (dec n-sum))]
            (and (not= m n) (= m-sum n))))]
    (->> pdivisors-seq
         (take limit)
         (rest)
         (filter amicable?)
         (map first)
         (reduce +))))

(defn problem21 []
  (exec-v2 "problem21"
           "Sum of all the amicable numbers under <NUMBER n>"
           main
           {:in [250] :out 220}
           {:in [300] :out 504}
           {:in [10000] :out 31626}))