(ns binary-search
  (:import
   [java.util Collections]))


(defn middle [coll]
  (quot (count coll) 2))


;; keep this for reference
(defn jbinary-search [n coll]
  (Collections/binarySearch coll n))


(defn cbinary-search [subject candidates]
  (loop [n                subject
         v                candidates
         start-idx        0
         excluded-end-idx (count v)]
    (let [mid-idx          (middle v)
          mid-val          (get v mid-idx)
          excluded-mid-idx (inc mid-idx)
          half-right-vec   #(subvec %1 excluded-mid-idx)
          half-left-vec    #(subvec %1 0 mid-idx)]
      (cond
        (empty? v) -1

        (> n mid-val)
        (recur
          n
          (half-right-vec v)
          (+ start-idx excluded-mid-idx)
          excluded-end-idx)

        (< n mid-val)
        (recur
          n
          (half-left-vec v)
          start-idx
          (- excluded-end-idx mid-idx))

        :else (+ start-idx mid-idx)))))


(defn search-for [n coll]
  (let [sorted-v (into [] (sort coll))
        idx      (cbinary-search n sorted-v)]
    (if (> idx -1)
      idx
      (throw (Exception. "not found")))))
