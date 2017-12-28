(ns hamming)


;; inspired by http://exercism.io/submissions/a90b56d8b1684597a12b528b81550632
(defn distance- [dna1 dna2]
  (let [each-distance #(if (= %1 %2) 0 1)]
    (->>
      (map each-distance dna1 dna2)
      (reduce +))))


(defn distance [dna1 dna2]
  (when (= (count dna1) (count dna2))
    (distance- dna1 dna2)))
