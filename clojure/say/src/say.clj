(ns say
  (:require [clojure.string :as string]))


(def single-word
  {0  "zero"
   1  "one"
   2  "two"
   3  "three"
   4  "four"
   5  "five"
   6  "six"
   7  "seven"
   8  "eight"
   9  "nine"
   10 "ten"
   11 "eleven"
   12 "twelve"
   13 "thirteen"
   14 "fourteen"
   15 "fifteen"
   16 "sixteen"
   17 "seventeen"
   18 "eighteen"
   19 "nineteen"
   20 "twenty"
   30 "thirty"
   40 "forty"
   50 "fifty"
   60 "sixty"
   70 "seventy"
   80 "eighty"
   90 "ninety"})


(defn- single-word-coll [x]
  (loop [y x
         coll []]
    (cond
      (zero? y)
      (if (not-empty coll)
        coll
        (conj coll (single-word y)))

      (<= 1000 y)
      (recur
        (rem y 1000)
        (-> coll
            (conj (single-word (quot y 1000)))
            (conj "thousand")))

      (<= 100 y)
      (recur
        (rem y 100)
        (-> coll
            (conj (single-word (quot y 100)))
            (conj "hundred")))

      (zero? (rem y 10))
      (conj coll (single-word y))

      (<= 20 y)
      (conj coll (str
                   (single-word (- y (rem y 10)))
                   "-"
                   (single-word (rem y 10))))

      :else
      (conj coll (single-word y)))))


(defn chunk-into-thousands [x]
  (loop [y x
         coll []]
    (if (< y 1000)
      (conj coll y)
      (recur
        (quot y 1000)
        (conj coll (rem y 1000))))))


(defn- number* [x]
  (let [[hundred-number & more-number-coll] (chunk-into-thousands x)
        thousands-word (map #(conj (single-word-coll %1) %2)
                            more-number-coll
                            ["thousand"])
        hundreds-word (single-word-coll hundred-number)]
    (->>
      (cons hundreds-word thousands-word)
      (map #(string/join " " %))
      (reverse)
      (string/join " ")
      (#(string/replace % " zero" "")))))


(defn number [x]
  (if (and (< -1 x) (< x 1e12))
    (number* x)
    (throw (IllegalArgumentException.))))
