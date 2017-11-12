(ns binary-search
  (:import
   [java.util Collections]))


(defn middle [coll]
  (quot (count coll) 2))


;; keep this for reference
(defn jbinary-search [n coll]
  (Collections/binarySearch coll n))


(defn binary-search-step [[n v start-idx excluded-end-idx]]
  (let [mid-idx          (middle v)
        mid-val          (get v mid-idx)
        excluded-mid-idx (inc mid-idx)]
    (cond
      (empty? v) -1
      (> n mid-val) [n (subvec v excluded-mid-idx) (+ start-idx excluded-mid-idx) excluded-end-idx]
      (< n mid-val) [n (subvec v 0 mid-idx) start-idx (- excluded-end-idx mid-idx)]
      :else (+ start-idx mid-idx))))


(defn cbinary-search [n v]
  (let [fixed-point?              (complement coll?)
        binary-search-fixed-point #(if (fixed-point? %1) %1 (binary-search-step %1))]
    (->> [n v 0 (count v)]
      (iterate binary-search-fixed-point)
      (filter fixed-point?)
      (first))))


(defn search-for [n coll]
  (let [sorted-v (into [] (sort coll))
        idx      (cbinary-search n sorted-v)]
    (if (> idx -1)
      idx
      (throw (Exception. "not found")))))
