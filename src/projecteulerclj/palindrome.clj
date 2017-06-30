(ns projecteulerclj.palindrome)

(defn palindrome? [string]
  (if (empty? string)
    true
    (if (= (first string) (last string))
      (palindrome? (butlast (rest string)))
      false)))
