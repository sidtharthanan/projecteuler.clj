(ns projecteulerclj.lib)

;(defn gcd
;  ([a b]
;   (if (zero? (rem a b)) b (gcd b (rem a b))))
;  ([a b & args]
;   (apply gcd (gcd a b) args)))

(def rem? #(not (zero? (rem %1 %2))))

(defn divisible-by? [dividend divisor]
  (zero? (rem dividend divisor)))

(defn take-below [max-xcld range]
  (take-while #(< % max-xcld) range))

(defn gcd [a b]
  "greatest common divisor"
  (if (divisible-by? a b) b (gcd b (rem a b))))

(defn lcm [& args]
  "least common multiple"
  (reduce #(/ (* %1 %2) (gcd %1 %2)) args))

(defn prime-factor-limit [number]
  (inc (Math/ceil (Math/sqrt number))))

(defn ^:private prime-candidates
  ([] (cons 2 (cons 3 (prime-candidates 1))))
  ([n] (cons (dec (* 6 n))
             (cons (inc (* 6 n))
                   (lazy-seq (prime-candidates (inc n)))))))

(defn lpf [number]
  "least prime factor"
  (if (> number 2)
    (some (fn [factor] (if (divisible-by? number factor) factor))
          (take-below (prime-factor-limit number) (prime-candidates)))))

(defn prime? [number] (not (lpf number)))

(defn primes
  ([] (filter prime? (prime-candidates)))
  ([max-x] (take-below max-x (primes))))

(defn frame [size coll]
  (if (<= size (count coll))
    (cons (take size coll) (lazy-seq (frame size (rest coll))))))

(def frame #(partition %1 1 %2))

(defn pythagorean? [a b c]
  (and (< a b c) (= (+ (* a a) (* b b)) (* c c))))

(defn find-by [[first & rest] pred]
  (if (pred first)
    first
    (if rest (recur rest pred))))
