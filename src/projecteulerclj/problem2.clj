(in-ns 'projecteulerclj.core)

;This is to find out the possible position of the given number in the sequence
;(defn approx-depth
;  ([number] (approx-depth number 0))
;  ([number cnt]
;   (if (> (Math/floor number) 2)
;     (approx-depth (Math/ceil (/ number 1.61803398875)) (inc cnt))
;     (inc cnt))))

(defn fibo
  ([] (fibo 1 2))
  ([a b] (cons a (lazy-seq (fibo b (+' a b))))))

(defn main [upto]
  (->> (fibo)
       (filter even?)
       (take-while #(> upto %))
       (reduce +')))

(defn problem2 []
  (println "fibonacci -> even? -> below 100000 -> total " (main 100000))
  (println "fibonacci -> even? -> below 4000000 -> total " (main 4000000)))

;(defn positive-numbers1
;  ([] (positive-numbers1 1))
;  ([n] (println n) (cons n (lazy-seq (positive-numbers1 (inc n))))))
;
;(defn positive-numbers2
;  ([] (positive-numbers2 1))
;  ([n] (println n) (lazy-seq (cons n (positive-numbers2 (inc n))))))


