(ns armstrong-numbers)

(defn armstrong? [n]
  (let [digits (map #(Integer/parseInt (str %)) (str n))
        ret (reduce + (map #(Math/pow %1 (count digits)) digits))]
    (zero? (- n ret))))
