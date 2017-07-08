(ns projecteulerclj.test-suite)

(defn log [& args] (println (apply str args)))

(defn exec-one [main input expected]
  (log "INPUT :" input)
  (log "EXPECTED :" expected)
  (let [actual (apply main input)]
    (if (= expected actual)
      (log "STATUS : OK")
      (do (log "STATUS :" "FAIL" "*******************************")
          (log "ACTUAL :" actual))))
  (log "________________________________"))

(defn exec [name desc main & cases]
  (log "=======================================================")
  (log "Problem :" name)
  (log "Desc :" desc)
  (log "=======================================================")
  (doseq [case cases
          :let [[[& input] expected] case]]
    (exec-one main input expected)))

(defn exec1.1 [name desc main & ins-outs]
  (log "=======================================================")
  (log "Problem :" name)
  (log "Desc :" desc)
  (log "=======================================================")
  (loop [rest-ins-outs ins-outs]
    (when rest-ins-outs
      (exec-one main (first rest-ins-outs) (second rest-ins-outs))
      (recur (nnext rest-ins-outs)))))
