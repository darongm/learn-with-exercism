(ns hamming)


(defn distance- [dna1 dna2]
  (->>
    (map = dna1 dna2)
    (filter false?)
    (count)))


(defn distance [dna1 dna2]
  (when (= (count dna1) (count dna2))
    (distance- dna1 dna2)))
