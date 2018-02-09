(ns armstrong-numbers)

(defn armstrong? [n]
  (let [digits (map #(Character/getNumericValue ^Character %) (str n))
        digit-count (count digits)
        sum (->> digits (map #(Math/pow %1 digit-count)) (reduce +))]
    (zero? (- n sum))))
