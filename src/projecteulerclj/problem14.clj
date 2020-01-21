(ns projecteulerclj.problem14
  (:require [projecteulerclj.test-suite :refer :all]))

(defn collatz-seq
  [n]
  (cons n
        (lazy-seq
          (if (< 1 n)
            (collatz-seq
             (if (even? n)
               (/ n 2)
               (inc (* n 3))))))))

(defn main [limit]
  (first (apply max-key count (map collatz-seq (range limit)))))

(defn problem14 []
  (exec-v2 "problem14"
           "Collatz sequence: Which starting number, under <NUMBER n>, produces the longest chain?"
           main
           {:scene "Below 10 is 9" :in [10] :out 9}
           {:scene "Below 100 is 97" :in [100] :out 97}
           {:scene "Below 1000 is 871" :in [1000] :out 871}
           {:scene "Below 1000000 is 837799" :in [1000000] :out 837799}))
