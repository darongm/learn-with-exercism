(ns nucleotide-count)


(defn nucleotide-counts [dna]
  (->> dna
    (frequencies)
    (merge {\A 0 \T 0 \C 0 \G 0})))


(defn count [m dna]
  {:pre [(contains? #{\A \T \C \G} m)]}
  (-> dna
    (nucleotide-counts)
    (get m)))
