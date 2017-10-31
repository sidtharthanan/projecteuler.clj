(ns projecteulerclj.core
  (:require [clojure.string :as str]))

(defn symbolise [& args]
  (symbol (apply str args)))

(defn get-problem [number]
  (require (symbolise "projecteulerclj.problem" number))
  (ns-resolve (symbolise "projecteulerclj.problem" number) (symbolise "problem" number)))

(defn main [start end]
  (doseq [number (range start end)]
    (if-let [problem (get-problem number)]
      (problem)
      (println (format "problem %s not solved." number)))))

(defn -main
  "Projecteuler problem solutions"
  ([nth] (-main nth nth))
  ([start end] (main (Integer. start) (inc (Integer. end)))))
