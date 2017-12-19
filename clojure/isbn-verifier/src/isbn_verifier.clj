(ns isbn-verifier)


(defn parse-isbn [isbn]
  (->> isbn
    (re-seq #"(\d)-(\d)(\d)(\d)-(\d)(\d)(\d)(\d)(\d)-(\d|X)")
    (first)
    (rest)))


(defn valid-formula? [digits]
  (let [mod-11     #(mod % 11)
        factors    (range 1 (inc (count digits)))
        int-digits (map #(Integer/parseInt %) digits)]
    (->>
      (map * int-digits factors)
      (reduce +)
      (mod-11)
      (zero?))))


(defn isbn? [isbn]
  (let [digits (parse-isbn isbn)]
    (valid-formula? digits)))
