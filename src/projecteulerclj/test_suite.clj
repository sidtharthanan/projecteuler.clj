(ns projecteulerclj.test-suite)

(defn log [& args] (println (apply str args)))

(defn exec [name desc main & cases]
  (log "=======================================================")
  (log "Problem :" name)
  (log "Desc :" desc)
  (log "=======================================================")
  (doseq [case cases
          :let [[[& input] expected] case]]
    (log "INPUT :" input)
    (log "EXPECTED :" expected)
    (let [actual (apply main input)]
      (if (= expected actual)
        (log "STATUS : OK")
        (do (log "STATUS :" "FAIL" "*******************************")
            (log "ACTUAL :" actual))))
    (log "________________________________")))