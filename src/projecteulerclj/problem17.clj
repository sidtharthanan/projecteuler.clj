(ns projecteulerclj.problem17
  (:require [projecteulerclj.test-suite :refer :all]))

(def base-word-count-dict
  {0         0
   1         3
   2         3
   3         5
   4         4
   5         4
   6         3
   7         5
   8         5
   9         4
   10        3
   11        6
   12        6
   13        8
   14        8
   15        7
   16        7
   17        9
   18        8
   19        8
   :tens     {2 6
              3 6
              4 5
              5 5
              6 5
              7 7
              8 6
              9 6}
   :hundred  7
   :and      3
   :thousand 8})

(defn letters-in [n]
  (let [{One      1
         Tens     :tens
         Hundred  :hundred
         And      :and
         Thousand :thousand
         :as      counts} base-word-count-dict]
    (cond
      (< n 20)   (counts n)
      (< n 100)  (+ (Tens (quot n 10)) (counts (rem n 10)))
      (< n 1000) (let [x         (counts (quot n 100))
                       something (letters-in (rem n 100))]
                   (if
                     (zero? something)
                     (+ x Hundred)
                     (+ x Hundred And something)))
      (= n 1000) (+ One Thousand)
      :else      nil)))

(defn unit-tests []
  (exec-v2 "problem17 unit tests"
           "Number letter counts:
              3 => Three => 5;
              10 Ten => 3;
              22 => TwentyTwo => 9;
              100 => OneHundred => 10;
              115 => OneHundredAndFifteen => 20"
           letters-in
           {:in [1] :out 3}
           {:in [2] :out 3}
           {:in [3] :out 5}
           {:in [5] :out 4}
           {:in [10] :out 3}
           {:in [20] :out 6}
           {:in [30] :out 6}
           {:in [40] :out 5}
           {:in [24] :out 10}
           {:in [28] :out 11}
           {:in [100] :out 10}
           {:in [115] :out 20}
           {:in [342] :out 23}
           {:in [762] :out 23}
           {:in [800] :out 12}
           {:in [810] :out 18}
           {:in [840] :out 20}
           {:in [1000] :out 11}))

(defn main [n]
  (reduce +' (map letters-in (range (inc n)))))

(defn main-tests []
  (exec-v2 "problem17 main"
           "Number letter counts:
    5 => one, two, three, four, five => 3 + 3 + 5 + 4 + 4 => 19;
    150 => one, two,.., one hundred,.., one hundred and twenty one,...one hundred and fifty => 3 + 3 +..+ 10 +..+ 24 +..+ 18 => 1903
    1000 => ... => ,,, => 21124"
           main
           {:in [5] :out 19}
           {:in [7] :out 27}
           {:in [11] :out 45}
           {:in [150] :out 1903}
           {:in [1000] :out 21124}))

(defn problem17 []
  (unit-tests)
  (main-tests))