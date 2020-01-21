(ns projecteulerclj.problem14
  (:require [projecteulerclj.test-suite :refer :all]))

(defn merge-trees [[n1 l1 r1 :as t1] [n2 l2 r2 :as t2]]
  (cond
    (nil? t1) t2
    (nil? t2) t1
    :else     [n1
               (merge-trees l1 l2)
               (merge-trees r1 r2)]))

(defn insert-branch [tree branch [index & path]]
  (if (nil? index)
    (merge-trees tree branch)
    (assoc tree index (insert-branch (tree index) branch path))))

(defn common-path [tree n]
  (loop [[[node left right :as tree] path continue] [tree [] nil]]
    (cond
      (nil? tree) (if (nil? continue) nil (recur continue))
      (= node n)  path
      :else       (recur
                    [left
                     (conj path 1)
                     [right (conj path 2) continue]]))))

(defn collatz-next [[node :as tree]]
  (if (even? node)
    [(/ node 2) tree nil]
    [(inc (* node 3)) nil tree]))

(defn builder [tree n]
  (loop [[node :as branch] [n]]
    (let [path (common-path tree node)]
      (if
        (nil? path)
        (recur (collatz-next branch))
        (insert-branch tree branch path)))))

(defn collatz-tree [limit]
  (reduce
   builder
   [1]
   (range 3 limit)))

(defn main [n]
  (println "hi")
  (println (collatz-tree n)))

(defn problem14 []
  (exec-v2 "problem14"
           "Collatz sequence: Which starting number, under <NUMBER n>, produces the longest chain?"
           main
           {:scene "test" :in [10000] :out 1000}

           ;           {:scene "Below 8 is 7" :in [8] :out 7}
           ;           {:scene "Below 10 is 9" :in [10] :out 9}
           ;           {:scene "Below 15 is 9" :in [15] :out 9}
           ))
