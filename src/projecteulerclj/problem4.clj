(ns projecteulerclj.problem4
  (:require [projecteulerclj.palindrome :refer [palindrome?]]
            [projecteulerclj.test-suite :as tests]))

(defn big-palindrome [min max]
  (loop [factor1 min factor2 min big-palindrome (* min min)]
    (if (or (> factor1 max) (> factor2 max))
      (if (palindrome? (str big-palindrome)) big-palindrome)
      (if (= factor2 max)
        (recur (inc factor1)
               (int (Math/floor (/ big-palindrome factor1)))
               big-palindrome)
        (recur factor1
               (inc factor2)
               (let [product (* factor1 factor2)]
                 (if (palindrome? (str product)) product big-palindrome)))))))

(defn problem4 []
  (tests/exec "problem4" "MIN MAX -> range -> permutation pairs -> product -> filter palindrome -> max " big-palindrome
              [[1 9] 9]
              [[1 13] 121]
              [[10 99] 9009]
              [[10 11] nil]
              [[100 999] 906609]))