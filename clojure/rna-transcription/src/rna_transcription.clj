(ns rna-transcription)


(def dna->rna {\C \G
               \G \C
               \A \U
               \T \A})


(defn to-rna [nucleotides]
  {:pre [(every? dna->rna nucleotides)]}
  (->> nucleotides
    (map dna->rna)
    (apply str)))
