(ns binary-search
  (:import
   [java.util Collections]))


(defn middle [coll]
  (quot (count coll) 2))


(defn jbinary-search [n coll]
  (Collections/binarySearch coll n))


(defn search-for [n coll]
  (let [idx (jbinary-search n coll)]
    (if (> idx -1)
      idx
      (throw (Exception. "not found")))))
