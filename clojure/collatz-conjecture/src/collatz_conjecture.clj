(ns collatz-conjecture)


(defn apply-math [n]
  (if (even? n)
    (/ n 2)
    (+ (* 3 n) 1)))


(defn collatz- [n]
  (->> n
    (iterate apply-math)
    (take-while #(not= 1 %))
    (count)))


(defn collatz [n]
  (if (< n 1)
    (throw (IllegalArgumentException. "zero or negative value is an error"))
    (collatz- n)))
