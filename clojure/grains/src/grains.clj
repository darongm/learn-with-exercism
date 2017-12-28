(ns grains)


(defn take-power-of-2 [n]
  (take n (iterate (partial * 2) BigInteger/ONE)))


(defn square [n]
  (last (take-power-of-2 n)))


(defn total []
  (reduce + (take-power-of-2 64)))
