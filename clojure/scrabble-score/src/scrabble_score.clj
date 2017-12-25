(ns scrabble-score
  (:require
   [clojure.string :as string]))


;; inspired the hash map naming from http://www.lispcast.com/clojure-hashmaps
(def score-by-letter
  (let [scores        {["a" "e" "i" "o" "u" "l" "n" "r" "s" "t"] 1
                       ["d" "g"]                                 2
                       ["b" "c" "m" "p"]                         3
                       ["f" "h" "v" "w" "y"]                     4
                       ["k"]                                     5
                       ["j" "x"]                                 8
                       ["q" "z"]                                 10}
        split-key-map (fn [ks v] (zipmap ks (repeat v)))]
    (reduce-kv #(merge %1 (split-key-map %2 %3)) {} scores)))


(defn score-letter [letter]
  {:pre [(or (char? letter) (= 1 (count letter)))]}
  (->> letter
    (string/lower-case)
    (get score-by-letter)))


(defn score-word [word]
  (->> word
    (map score-letter)
    (reduce +)))
