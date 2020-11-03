(ns projecteulerclj.problem32
  (:require [projecteulerclj.test-suite :refer :all]))

(defn subdigits
  "convert n to string do substring then convert back to int"
  [n start end]
  (let [len (inc (Math/floor (Math/log10 n)))]
    (cond
      (nil? start) (int (rem n (Math/pow 10 (- len end))))
      (nil? end)   (int (quot n (Math/pow 10 (- len start))))
      :else        (int (quot (rem n (Math/pow 10 (- len start))) (Math/pow 10 (- len end)))))))

(defn splits [n]
  (let [cnt (inc (Math/floor (Math/log10 n)))]
    (for [x (range 1 (dec cnt))
          y (range 1 (- cnt x))]
      [(subdigits n x nil)
       (subdigits n x (+ x y))
       (subdigits n nil (+ x y))])))

(defn variations [invec]
  (if (next invec)
    (for [x invec
          y (variations (remove #{x} invec))]
      (+ (* 10 y) x))
    invec))

(defn main []
  (->> (variations [1 2 3 4 5 6 7 8 9])
       (map splits)
       (mapcat #(filter (fn [[f1 f2 p]] (== (* f1 f2) p)) %))
       (map last)
       (set)
       (reduce +)))

(defn splits1 [nstr]
  (let [cnt (count nstr)]
    (for [x (range 1 (dec cnt))
          y (range 1 (- cnt x))]
      [(subs nstr 0 x)
       (subs nstr x (+ x y))
       (subs nstr (+ x y) cnt)])))

(defn variations1 [invec]
  (println "came here" invec)
  (if (next invec)
    (do
      (println "start")
      (for [x invec
            y (variations1 (remove #{x} invec))]
        (do (println "done" (str y x))
          (str y x))))
    (apply str invec)))

(defn main1 []
  (->> (variations1 [1 2 3 4 5 6 7 8 9])
       (map splits1)
       (reduce concat)
       (map #(map bigint %))
       (filter (fn [[f1 f2 p]] (== (* f1 f2) p)))
       (map last)
       (set)
       (reduce +)))

(defn problem32 []
  (exec-v2 "problem32"
           ""
           main1
           {:in [] :out 45228}))
