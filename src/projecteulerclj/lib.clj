(ns projecteulerclj.lib)

;(defn gcd
;  ([a b]
;   (if (zero? (rem a b)) b (gcd b (rem a b))))
;  ([a b & args]
;   (apply gcd (gcd a b) args)))

(defn gcd [a b]
  (if (zero? (rem a b)) b (gcd b (rem a b))))

(defn lcm [& args]
  (reduce #(/ (* %1 %2) (gcd %1 %2)) args))

(defn prime-factor-limit [number]
  (inc (Math/ceil (Math/sqrt number))))

(defn prime? [number]
  (if (> 10 number)
    (.contains [2 3 5 7] number)
    (not-any?
      #(zero? (mod number %)) (range 2 (prime-factor-limit number)))))

(defn ^:private prime-candidates
  ([] (cons 2 (cons 3 (prime-candidates 1))))
  ([n] (cons (dec (* 6 n))
             (cons (inc (* 6 n))
                   (lazy-seq (prime-candidates (inc n)))))))

(defn primes
  ([] (filter prime? (prime-candidates)))
  ([max-x] (take-while #(< % max-x) (primes))))
