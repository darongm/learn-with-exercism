(ns rna-transcription)


(def dna-rna-map {\C \G
                  \G \C
                  \A \U
                  \T \A})


(defn dna->rna [n]
  {:pre [(contains? dna-rna-map n)]}
  (get dna-rna-map n))


(defn to-rna [dna]
  (->> dna
    (map dna->rna)
    (apply str)))
