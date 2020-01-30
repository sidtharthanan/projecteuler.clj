(ns projecteulerclj.problem26
  (:require [projecteulerclj.test-suite :refer :all]))

(defn unit-frac-to-dec [denom]
  (loop [dec-rep []
         n       1]
    (let [q       (quot (* 10 n) denom)
          r       (rem (* 10 n) denom)]
      (if (some #{[q r]} dec-rep)
        (let [[left right] (split-at (.indexOf dec-rep [q r]) (map first dec-rep))]
          (concat left [right]))
        (recur (conj dec-rep [q r]) r)))))

(defn main [limit]
  (->> (range 1 limit)
       (map (comp count last unit-frac-to-dec))
       (map vector (range 1 limit))
       (apply max-key second)
       (first)))

(defn problem26 []
  (exec-v2 "problem26"
           "Find the unit fraction with the longest Reciprocal cycles such that the denominator d < INPUT."
           main
           {:in [10] :out 7}
           {:in [100] :out 97}
           {:in [1000] :out 983}))
