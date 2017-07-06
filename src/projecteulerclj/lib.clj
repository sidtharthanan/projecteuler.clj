(ns projecteulerclj.lib)

;(defn gcd
;  ([a b]
;   (if (zero? (rem a b)) b (gcd b (rem a b))))
;  ([a b & args]
;   (apply gcd (gcd a b) args)))

(def rem? #(not (zero? (rem %1 %2))))

(defn take-till [max-xcld range]
  (take-while #(< % max-xcld) range))

(defn gcd [a b]
  (if-not (rem? a b) b (gcd b (rem a b))))

(defn lcm [& args]
  (reduce #(/ (* %1 %2) (gcd %1 %2)) args))

(defn prime-factor-limit [number]
  (inc (Math/ceil (Math/sqrt number))))

(defn ^:private prime-candidates
  ([] (cons 2 (cons 3 (prime-candidates 1))))
  ([n] (cons (dec (* 6 n))
             (cons (inc (* 6 n))
                   (lazy-seq (prime-candidates (inc n)))))))

(defn prime? [number]
  (every?
    #(rem? number %) (take-till (prime-factor-limit number) (rest (prime-candidates)))))

(defn primes
  ([] (filter prime? (prime-candidates)))
  ([max-x] (take-till max-x (primes))))
