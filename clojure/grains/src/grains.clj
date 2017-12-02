(ns grains)


(defn grain-seq [n]
  (take n (iterate (partial * 2) (biginteger 1))))


(defn square [n]
  (last (grain-seq n)))


(defn total []
  (reduce + (grain-seq 64)))
