(ns armstrong-numbers)


(defn digits [n]
  (map #(Character/getNumericValue ^Character %) (str n)))


(defn sum-digits-raised-to-power [digit-coll]
  (let [digit-count (count digit-coll)]
    (->> digit-coll
         (map #(Math/pow %1 digit-count))
         (reduce +))))


(defn armstrong? [n]
  (-> n
      (digits)
      (sum-digits-raised-to-power)
      (== n)))
