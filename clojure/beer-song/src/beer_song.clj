(ns beer-song
  (:require
   [clojure.string :as string]))


(defn verse [n]
  (let [verse-0 (str
                  "No more bottles of beer on the wall, no more bottles of beer.\n"
                  "Go to the store and buy some more, 99 bottles of beer on the wall.\n")
        verse-1 (str
                  "1 bottle of beer on the wall, 1 bottle of beer.\n"
                  "Take it down and pass it around, no more bottles of beer on the wall.\n")
        verse-2 (str
                  "2 bottles of beer on the wall, 2 bottles of beer.\n"
                  "Take one down and pass it around, 1 bottle of beer on the wall.\n")
        verse-n (str
                  n " bottles of beer on the wall, " n " bottles of beer.\n"
                  "Take one down and pass it around, " (dec n) " bottles of beer on the wall.\n")]
    (cond
      (= 0 n) verse-0
      (= 1 n) verse-1
      (= 2 n) verse-2
      :else verse-n)))


(defn sing
  ([end] (sing end 0))
  ([end start]
   (let [include-start (dec start)]
     (->>
       (range end include-start -1)
       (map verse)
       (string/join "\n")))))
