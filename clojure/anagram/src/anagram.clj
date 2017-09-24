(ns anagram
  (:require
   [clojure.string :as string]))


;; inspired by http://exercism.io/submissions/1f62653037ae47da9fe8fd9d2915d090
(defn anagram? [word1 word2]
  (let [lc-word1 (string/lower-case word1)
        lc-word2 (string/lower-case word2)]
    (and
      (not= lc-word1 lc-word2)
      (= (frequencies lc-word1) (frequencies lc-word2)))))


(defn anagrams-for [word coll]
  (filterv #(anagram? word %) coll))
