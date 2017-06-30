(ns projecteulerclj.core
  (:require [clojure.string :as str]))

(defn symbolise [& args]
  (symbol (apply str args)))

(defn get-problem [number]
  (require (symbolise "projecteulerclj.problem" number))
  (ns-resolve (symbolise "projecteulerclj.problem" number) (symbolise "problem" number)))

(defn -main
  "Projecteuler problem solutions"
  ([number]
   (if-let [problem (get-problem number)]
     (problem)
     (println (format "problem %s not solved." number))))
  ([start end]
   (doseq [number (range (Integer. start) (inc (Integer. end)))]
     (-main number))))
