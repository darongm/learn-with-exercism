(ns scrabble-score)


(defn score-letter [letter]
  (let [value->letter {1  ["a" "e" "i" "o" "u" "l" "n" "r" "s" "t"]
                       2  ["d" "g"]
                       3  ["b" "c" "m" "p"]
                       4  ["f" "h" "v" "w" "y"]
                       5  ["k"]
                       8  ["j" "x"]
                       10 ["q" "z"]}
        letter->value (->> value->letter
                        (mapcat (fn [[k v]] (zipmap v (repeat k))))
                        (into (hash-map)))]
    (letter->value letter)))


(defn score-word [_])
