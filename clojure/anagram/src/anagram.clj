(ns anagram
  (:require
   [clojure.string :as string]))


(defn anagram? [word1 word2]
  (let [lc-word1    (string/lower-case word1)
        lc-word2    (string/lower-case word2)
        letter-coll #(->> % vec frequencies)]
    (and
      (not= lc-word1 lc-word2)
      (= (letter-coll lc-word1) (letter-coll lc-word2)))))


(defn anagrams-for [word coll]
  (filterv #(anagram? word %) coll))
