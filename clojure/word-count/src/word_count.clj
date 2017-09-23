(ns word-count)


(defn word-count [words]
  (let [letter?   (fn [^Character c] (and
                                       (Character/isLetter c)
                                       (not (Character/isSpace c))))
        to-string (fn [coll] (apply str coll))]
    (->> words
      (partition-by letter?)
      (map to-string)
      (frequencies))))
