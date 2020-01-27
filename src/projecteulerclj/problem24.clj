(ns projecteulerclj.problem24
  (:require [projecteulerclj.test-suite :refer :all]
            [projecteulerclj.lib :refer [get-factorial-seq]]))

(def factorial-seq (get-factorial-seq))

(defn nth-perm [n perm]
  (if (== n 0)
    perm
    (let [[skip-perms swap-tail]       (last (take-while #(<= (first %) n) factorial-seq))
          swap-index                   (- (count perm) (inc swap-tail))
          swap-char                    (perm swap-index)
          [co-swap-index co-swap-char] (->> perm
                                            (map vector (range))
                                            (take-last swap-tail)
                                            (filter #(< swap-char (second %)))
                                            (apply min-key second))]
      (recur (- n skip-perms)
        (assoc perm swap-index co-swap-char co-swap-index swap-char)))))

(defn main [chars n]
  (apply str (nth-perm (dec n) chars)))

(defn problem24 []
  (exec-v2 "problem24"
           "Lexicographic permutation"
           main
           {:in [[0 1 2 3 4 5] 1] :out "012345"}
           {:in [[0 1 2 3 4 5] 2] :out "012354"}
           {:in [[0 1 2 3 4 5] 3] :out "012435"}
           {:in [[0 1 2 3 4 5] 4] :out "012453"}
           {:in [[0 1 2 3 4 5] 5] :out "012534"}
           {:in [[0 1 2 3 4 5] 6] :out "012543"}
           {:in [[0 1 2 3 4 5] 7] :out "013245"}
           {:in [[0 1 2 3 4 5] 8] :out "013254"}
           {:in [[0 1 2 3 4 5] 9] :out "013425"}
           {:in [[0 1 2 3 4 5] 10] :out "013452"}
           {:in [[0 1 2 3 4 5] 18] :out "014532"}
           {:in [[0 1 2 3 4 5] 22] :out "015342"}
           {:in [[0 1 2 3 4 5 6 7 8 9] 1000000] :out "2783915460"}))
