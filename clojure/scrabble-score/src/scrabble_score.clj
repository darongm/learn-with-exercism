(ns scrabble-score
  (:require
   [clojure.string :as string]))


(defn score-letter [letter]
  {:pre [(or (char? letter) (= 1 (count letter)))]}
  (let [scores        {["a" "e" "i" "o" "u" "l" "n" "r" "s" "t"] 1
                       ["d" "g"]                                 2
                       ["b" "c" "m" "p"]                         3
                       ["f" "h" "v" "w" "y"]                     4
                       ["k"]                                     5
                       ["j" "x"]                                 8
                       ["q" "z"]                                 10}
        letter->value (fn [k v] (zipmap k (repeat v)))
        ->value       (reduce-kv #(merge %1 (letter->value %2 %3)) {} scores)]
    (-> letter
      (string/lower-case)
      (->value))))


(defn score-word [word]
  (->> word
    (map score-letter)
    (reduce +)))
