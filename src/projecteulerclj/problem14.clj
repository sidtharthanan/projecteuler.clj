(ns projecteulerclj.problem14
  (:require [projecteulerclj.test-suite :refer :all]))

(defrecord Tree [node left right])

(defn tovec [t]
  [(:node t)
   (and (:left t) (tovec (:left t)))
   (and (:right t) (tovec (:right t)))])

(defn totree [[node left right :as t]]
  (if (not (nil? t)) (Tree. node (totree left) (totree right))))

(defn merge-trees [t1 t2]
  (cond
    (nil? t1) t2
    (nil? t2) t1
    :else     (Tree. (:node t1) (merge-trees (:left t1) (:left t2)) (merge-trees (:right t1) (:right t2)))))

(defn insert-branch [tree branch [side & path]]
  (if (nil? side)
    (merge-trees tree branch)
    (assoc tree side (insert-branch (get tree side) branch path))))

(defn common-path [tree node]
  (loop [[tree path continue] [tree [] nil]]
    (cond
      (nil? tree)                (if (nil? continue) nil (recur continue))
      (= (:node tree) node)      path
      :else                      (recur
                                   [(:left tree)
                                    (conj path :left)
                                    [(:right tree) (conj path :right) continue]]))))

(defn collatz-next [tree]
  (if (even? (:node tree))
    (Tree. (/ (:node tree) 2) tree nil)
    (Tree. (inc (* (:node tree) 3)) nil tree)))

(defn builder [tree n]
  (loop [branch (Tree. n nil nil)]
    (let [path (common-path tree (:node branch))]
      (if
        (nil? path)
        (recur (collatz-next branch))
        (insert-branch tree branch path)))))

(defn collatz-tree [limit]
  (reduce
   builder
   (Tree. 1 nil nil)
   (range 3 limit)))

(defn main [n]
  (println "hi")
  (println (tovec (collatz-tree n))))

(defn problem14 []
  (exec-v2 "problem14"
           "Collatz sequence: Which starting number, under <NUMBER n>, produces the longest chain?"
           main
           {:scene "test" :in [10000] :out 1000}
;           {:scene "Below 8 is 7" :in [8] :out 7}
;           {:scene "Below 10 is 9" :in [10] :out 9}
;           {:scene "Below 15 is 9" :in [15] :out 9}
           ))
