(ns say)


(defn number- [n])


(defn number [n]
  (if (and (< -1 n) (< n 1e12))
    (number- n)
    (throw (IllegalArgumentException.))))
