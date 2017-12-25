(ns scrabble-score
  (:require
   [clojure.string :as string]))


(defn score-letter [letter]
  {:pre [(or (char? letter) (= 1 (count letter)))]}
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
    (-> letter (string/lower-case) (letter->value))))


(defn score-word [_])
