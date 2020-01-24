(ns projecteulerclj.problem19
  (:require [projecteulerclj.test-suite :refer :all]))

(defn leap? [yr]
  (if
    (zero? (rem yr 100))
    (zero? (rem yr 400))
    (zero? (rem yr 4))))

(defn month-day-shift-map
  "Jan through Dec (mod (days-in month) 7)"
  [year]
  (if (leap? year)
    [3 1 3 2 3 2 3 3 2 3 2 3]
    [3 0 3 2 3 2 3 3 2 3 2 3]))

(def day-index
  {:sun 0
   :mon 1
   :tue 2
   :wed 3
   :thu 4
   :fir 5
   :sat 6})

(defn day-of-month-starts [year])

(defn months-start-on-sunday [y1-begin-day, y1, y2]
  (count
   (filter
    (partial = (day-index :sun))
    (reduce
     #(concat %1 [(mod (+ (last %1) %2) 7)])
     [(day-index y1-begin-day)]
     (flatten (map month-day-shift-map (range y1 (inc y2))))))))


(defn problem19 []
  (exec-v2 "problem19 main"
           "Find no of months starting on a Sunday from <YEAR n> till <YEAR m> inclusive."
           months-start-on-sunday
           {:in [:wed, 2020, 2020] :out 2}
           {:in [:wed, 2020, 2021] :out 3}
           {:in [:tue, 1901, 2000] :out 171}))
