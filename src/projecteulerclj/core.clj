(ns projecteulerclj.core
  ;(:gen-class)
  (:require [clojure.string :as str]))

(let [solved 3] (apply load
                       (map #(str "problem" (inc %)) (range solved))))

(defn get-problem [number]
  (ns-resolve 'projecteulerclj.core (symbol (str "problem" number))))

(defn -main
  "Projecteuler problem solutions"
  [number & args]
  (if-let [problem-sol (get-problem number)]
    (problem-sol)
    (println (format "problem %s not solved." number))))
