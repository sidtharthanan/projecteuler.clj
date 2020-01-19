(ns projecteulerclj.problem3
  (:require [projecteulerclj.test-suite :as tests]
            [projecteulerclj.lib :refer [primes prime-factor-limit smallest-divisor]]))

(defn factorize
  ([number] (factorize number (set nil)))
  ([number factors]
   (if-let [factor (smallest-divisor number)]
     (factorize (/ number factor) (conj factors factor))
     (conj factors number))))

(defn factorize-1 [product]
  (loop [dividend product factors []]
    (if-let [factor (smallest-divisor dividend)]
      (recur (/ dividend factor) (conj factors factor))
      (conj factors dividend))))

(defn factorize-2 [number]
  (fn [factors]
    (if-let [factor (smallest-divisor number)]
      ((factorize-2 (/ number factor)) (conj factors factor))
      (conj factors number))))

(defn main [number]
  (apply max (factorize number)))

(defn main-1 [number]
  (apply max (factorize-1 number)))

(defn main-2 [number]
  (apply max ((factorize-2 number) (set nil))))

(defn problem3 []
  (tests/exec "problem3" "INPUT -> factos -> primes -> max ->" main
              [[13195] 29]
              [[600851475143] 6857]))