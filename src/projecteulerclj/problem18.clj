(ns projecteulerclj.problem18
  (:require [projecteulerclj.test-suite :refer :all]
            [projecteulerclj.lib :refer [frame-x]]
            [clojure.string :refer [split trim]]))

(def data1 "3\n7 4\n2 4 6\n8 5 9 3")
(def data2 "3\n7 4\n2 4 9\n8 5 9 9")
(def data3
  "75
 95 64
 17 47 82
 18 35 87 10
 20 04 82 47 65
 19 01 23 75 03 34
 88 02 77 73 07 63 67
 99 65 04 28 06 16 70 92
 41 41 26 56 83 40 80 70 33
 41 48 72 33 47 32 37 16 94 29
 53 71 44 65 25 43 91 52 97 51 14
 70 11 33 28 77 73 17 78 39 68 17 57
 91 71 52 38 17 14 91 43 58 50 27 29 48
 63 66 04 68 89 53 67 30 73 16 69 87 40 31
 04 62 98 27 23 09 70 98 73 93 38 53 60 04 23")

(defn to-lists [datastr]
  (map
   (fn parseline [s]
     (map
      (comp #(Integer. %) str)
      (split (trim s) #" ")))
   (seq (split (trim datastr) #"\n"))))

(defn adjacent-maxes [row]
  (concat [(first row)] (map #(apply max %) (frame-x 2 row)) [(last row)]))

(defn main [datastr]
  (let [[row1 & rows] (to-lists datastr)]
    (loop [max-acc row1
           rs      rows]
      (if (empty? rs)
        (apply max max-acc)
        (recur (map + (adjacent-maxes max-acc) (first rs)) (rest rs))))))

(defn problem18 []
  (exec-v2 "problem18 main"
           "Maximum path sum I. Finding the best route on a triangle."
           main
           {:in [data1] :out 23}
           {:in [data2] :out 25}
           {:in [data3] :out 1074}))

