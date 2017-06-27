(in-ns 'projecteulerclj.core)

(def divisible? #(zero? (rem %1 %2)))
(def div-by-three? #(divisible? % 3))
(defn div-by-five? [num] (divisible? num 5))
(def natural #(range 1 %))

(defn main [upto]
  (reduce + (filter (some-fn div-by-five? div-by-three?) (natural upto))))

(defn problem1 []
  (println (main 10))
  (println (main 1000)))

;(def fizzbuzz
;  (some-fn #(and (= (mod % 3) 0) (= (mod % 5) 0) "FizzBuzz")
;           #(and (= (mod % 3) 0) "Fizz")
;           #(and (= (mod % 5) 0) "Buzz")
;           str))
;
;(run! println (map fizzbuzz (range 1 18)))
;(defn filter-or [& args]
;  (let [fns (butlast args)
;        sequence (last args)]
;    (filter (apply some-fn fns) sequence)))
;
;(defn filter-and [& args]
;  (let [fns (butlast args)
;        sequence (last args)]
;    (filter (apply every-pred fns) sequence)))
