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
