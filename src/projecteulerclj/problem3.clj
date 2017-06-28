(ns projecteulerclj.problem3
  (:require [projecteulerclj.test-suite :as tests]))

;(defn prime-candidates
;  ([] (cons 2 (cons 3 (prime-candidates 1))))
;  ([n]
;   (cons (-> n (* 6) (- 1))
;         (cons (-> n (* 6) (+ 1))
;               (lazy-seq (prime-candidates (inc n)))))))

(defn apprx-sqrt [number]
  (inc (Math/ceil (Math/sqrt number))))

(defn prime? [number]
  (if (> 10 number)
    (.contains [2 3 5 7] number)
    (not-any?
      #(zero? (mod number %)) (range 2 (apprx-sqrt number)))))

(defn primes
  ([] (primes 10))
  ([till] (filter prime? (range 2 till))))

(defn factorize
  ([number] (factorize number (set nil)))
  ([number factors]
   (if (= number 1) factors (if-let [factor (some #(and (zero? (mod number %)) %) (primes (apprx-sqrt number)))]
                              (factorize (/ number factor) (conj factors factor))
                              (conj factors number)))))
(defn main [number]
  (apply max (factorize number)))

(defn problem3 []
  (tests/exec "problem3" "INPUT -> factos -> primes -> max ->" main
              [[13195] 29]
              [[600851475143] 6857]))