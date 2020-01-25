(ns projecteulerclj.problem22
  (:require [projecteulerclj.test-suite :refer :all]
            [clojure.string :as str]))

(def data1 (slurp "src/projecteulerclj/data/22.txt"))

(defn main [data]
  (let [get-worth    (fn [name] (reduce + (map #(- (int %) 64) name)))
        get-score    (fn [rank name] (*' rank (get-worth name)))
        sorted-names (sort (str/split (str/replace data #"\"" "") #","))]
    (reduce +' (map get-score (rest (range)) sorted-names))))

(defn problem22 []
  (exec-v2 "Problem22"
           "Name scores"
           main
           {:scene "Sample data from project euler site" :in [data1] :out 871198282}))
