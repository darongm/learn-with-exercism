(ns perfect-numbers)


(defn classify- [n]
  (let [factors (->> n (range 1) (filter #(= 0 (mod n %))))
        sum     (reduce + factors)]
    (cond
      (> n sum) :deficient
      (< n sum) :abundant
      :else :perfect)))


(defn classify [n]
  (if (< n 0)
    (throw (IllegalArgumentException.))
    (classify- n)))
