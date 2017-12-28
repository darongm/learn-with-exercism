(ns flatten-array)

(defn flatten [coll]
  (->> coll
    (clojure.core/flatten)
    (remove nil?)))
