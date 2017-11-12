(ns binary-search
  (:import
   [java.util Collections]))


(defn middle [coll]
  (quot (count coll) 2))


;; keep this for reference
(defn jbinary-search [n coll]
  (Collections/binarySearch coll n))


(defn cbinary-search [[n v start-idx ex-end-idx]]
  (let [mid-idx (middle v)
        mid-val (get v mid-idx)]
    (cond
      (empty? v) -1
      (> n mid-val) [n (subvec v (inc mid-idx)) (+ start-idx mid-idx 1) ex-end-idx]
      (< n mid-val) [n (subvec v 0 mid-idx) start-idx (- ex-end-idx mid-idx)]
      :else (+ start-idx mid-idx))))

(defn cbinary-search+ [n v]
  (->> [n v 0 (count v)]
    (iterate #(if (coll? %1) (cbinary-search %1) %1))
    (drop-while coll?)
    (first)))


(comment
  (def n 1)
  (def v [1 3 4 6 8 9 11])
  (cbinary-search+ 1 [1 3 4 6 8 9 11])
  (cbinary-search+ 3 [1 3 4 6 8 9 11])
  (cbinary-search+ 4 [1 3 4 6 8 9 11])
  (cbinary-search+ 6 [1 3 4 6 8 9 11])
  (cbinary-search+ 8 [1 3 4 6 8 9 11])
  (cbinary-search+ 9 [1 3 4 6 8 9 11])
  (cbinary-search+ 12 [1 3 4 6 8 9 11])
  (cbinary-search+ 4 [4])
  (cbinary-search 4 [1 3 4 6 8 9 11] 0 7)
  (cbinary-search 4 [1 3 4] 0 4)
  (cbinary-search 4 [4] 2 4)
  (cbinary-search 1 [1] 0 3)
  (cbinary-search :right 11 [8 9 11] 3)                     ;3
  (cbinary-search :right 11 [11] 5)                         ;3
  (cbinary-search 1 [1 3 4] 3)                              ;1
  (cbinary-search 1 [1] 1))                                 ;0



(defn search-for [n coll]
  (let [sorted-v (into [] (sort coll))
        idx      (cbinary-search+ n sorted-v)]
    (if (> idx -1)
      idx
      (throw (Exception. "not found")))))
