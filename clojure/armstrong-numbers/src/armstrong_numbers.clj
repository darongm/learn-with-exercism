(ns armstrong-numbers)

(defn armstrong? [n]
  (let [digits (map #(Integer/parseInt (str %)) (str n))
        digit-count (count digits)
        sum (->> digits (map #(Math/pow %1 digit-count)) (reduce +))]
    (zero? (- n sum))))
