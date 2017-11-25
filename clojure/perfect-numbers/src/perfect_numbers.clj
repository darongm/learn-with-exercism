(ns perfect-numbers)


(defn classify- [n]
  (let [factors (->> n (range 1) (filter #(zero? (mod n %))))
        sum     (reduce + factors)]
    (cond
      (> n sum) :deficient
      (< n sum) :abundant
      :else :perfect)))


(defn classify [n]
  (if (neg? n)
    (throw (IllegalArgumentException.))
    (classify- n)))
