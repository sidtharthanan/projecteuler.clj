(ns projecteulerclj.core
  (:require [clojure.string :as str]))

(defn get-problem [number]
  (require (symbol (str "projecteulerclj.problem" number)))
  (ns-resolve (symbol (str "projecteulerclj.problem" number)) (symbol (str "problem" number)))
  )

(defn -main
  "Projecteuler problem solutions"
  ([number]
   (if-let [problem (get-problem number)]
     (problem)
     (println (format "problem %s not solved." number))))
  ([start end]
   (doseq [number (range (Integer. start) (inc (Integer. end)))]
     (-main number))))
