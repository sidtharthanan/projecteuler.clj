(ns projecteulerclj.test-suite)

(defn log [& args] (println (apply str args)))

(defn describe [name desc]
  (log "=======================================================")
  (log "Problem :" name)
  (log "Desc :" desc)
  (log "======================================================="))

(defn exec-one [main input expected]
  (log "INPUT :" input)
  (log "EXPECTED :" expected)
  (let [actual (apply main input)]
    (if (= expected actual)
      (log "STATUS : OK")
      (do (log "STATUS :" "FAIL" "*******************************")
          (log "ACTUAL :" actual))))
  (log "________________________________"))

(defn exec-one-v2 [scene main input expected]
  (log "SCENE :" scene)
  (let [actual (apply main input)]
    (if (= expected actual)
      (log "STATUS : OK")
      (do (log "EXPECTED :" expected)
          (log "ACTUAL :" actual)
          (log "STATUS :" "FAIL" "*******************************"))))
  (log "________________________________"))

(defn exec [name desc main & cases]
  (describe name desc)
  (doseq [case cases
          :let [[[& input] expected] case]]
    (exec-one main input expected)))

(defn exec1.1 [name desc main & ins-outs]
  (describe name desc)
  (loop [rest-ins-outs ins-outs]
    (when rest-ins-outs
      (exec-one main (first rest-ins-outs) (second rest-ins-outs))
      (recur (nnext rest-ins-outs)))))

(defn exec-v2 [name desc main & in-outs]
  (describe name desc)
  (doseq [{input    :in
           expected :out
           scene    :scene} in-outs]
    (if scene
      (exec-one-v2 scene main input expected)
      (exec-one main input expected))))