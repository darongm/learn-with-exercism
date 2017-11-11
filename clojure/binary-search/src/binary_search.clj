(ns binary-search
  (:import
   [java.util Collections]))


(defn middle [coll]
  (quot (count coll) 2))


(defn jbinary-search [n coll]
  (Collections/binarySearch coll n))


(defn search-for [n coll]
  (let [sorted-coll (sort coll)
        idx         (jbinary-search n sorted-coll)]
    (if (> idx -1)
      idx
      (throw (Exception. "not found")))))
