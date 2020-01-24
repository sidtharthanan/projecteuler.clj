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

(defn ^:private prime-candidates-x
  ([] (cons 2 (cons 3 (prime-candidates-x 1))))
  ([n]
   (cons (dec (* 6 n))
         (cons (inc (* 6 n))
               (lazy-seq (prime-candidates-x (inc n)))))))

(defn prime-candidates []
  (lazy-cat [2 3]
            ((fn seqfn [n]
               (lazy-cat [(dec (* 6 n)) (inc (* 6 n))]
                         (seqfn (inc n))))
              1)))

(defn smallest-divisor [n]
  "smallest factor of n that is greater than 1. returns 1 for 1."
  (or
    (if (> n 2)
      (some (fn [factor] (if (divisible-by? n factor) factor))
            (take-below (prime-factor-limit n) (prime-candidates))))
   (if (< 1 n) n)))

(defn prime? [number] (= number (smallest-divisor number)))

(defn primes
  ([] (filter prime? (prime-candidates)))
  ([max-x] (take-below max-x (primes))))

(defn frame-x [size coll]
  (if (<= size (count coll))
    (cons (take size coll) (lazy-seq (frame-x size (rest coll))))))

(defn frame-y [size coll]
  (if (<= size (count coll))
    (lazy-cat (take size coll) (frame-y size (rest coll)))))

(def frame #(partition %1 1 %2))

(defn pythagorean? [a b c]
  (and (< a b c) (= (+ (* a a) (* b b)) (* c c))))

(defn find-by [[first & rest] pred]
  (if (pred first)
    first
    (if rest (recur rest pred))))

(defn factorial [n]
  (loop [p 1N
         n n]
    (if (zero? n) p (recur (* p n) (dec n)))))