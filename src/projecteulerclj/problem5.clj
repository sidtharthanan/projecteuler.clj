(ns projecteulerclj.problem5
  (:require [projecteulerclj.lib :refer [lcm]]
            [projecteulerclj.test-suite :as tests]))


(defn problem5 []
  (tests/exec "problem5" "range -> lcm ->" lcm
              [[2 3] 6]
              [[3 4] 12]
              [[12 5] 60]
              [(range 1 11) 2520]
              [(range 1 21) 232792560]))
