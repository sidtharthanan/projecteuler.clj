(ns projecteulerclj.problem3
  (:require [projecteulerclj.test-suite :as tests]
            [projecteulerclj.lib :refer [primes prime-factor-limit lpf]]))

(defn factorize
  ([number] (factorize number (set nil)))
  ([number factors]
   (if-let [factor (lpf number)]
     (factorize (/ number factor) (conj factors factor))
     (conj factors number))))

(defn main [number]
  (apply max (factorize number)))

(defn problem3 []
  (tests/exec "problem3" "INPUT -> factos -> primes -> max ->" main
              [[13195] 29]
              [[600851475143] 6857]))