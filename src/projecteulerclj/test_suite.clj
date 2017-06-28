(ns projecteulerclj.test-suite)

(defn exec
  [name desc main & cases]
  (println (str name "-----------------------------------"))
  (println desc)
  (doseq [case cases
          :let [[[& input] expected] case]]
    (println "================")
    (println "INPUT :" input)
    (println "EXPECTED :" expected)
    (let [actual (apply main input)]
      (if (= expected actual)
        (println "STATUS : OK")
        (do (println "STATUS : FAIL********************************")
            (println "ACTUAL :" actual))))))