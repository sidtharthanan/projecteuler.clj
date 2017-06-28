(ns projecteulerclj.core
  (:require [clojure.string :as str]))

(defn get-problem [number]
  (require (symbol (str "projecteulerclj.problem" number)))
  (ns-resolve (symbol (str "projecteulerclj.problem" number)) (symbol (str "problem" number)))
  )

(defn -main
  "Projecteuler problem solutions"
  [number & args]
  (if-let [problem-sol (get-problem number)]
    (problem-sol)
    (println (format "problem %s not solved." number))))
