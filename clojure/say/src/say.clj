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
      (<= y 20)
      (conj coll (single-word y))

      (zero? (mod y 10))
      (conj coll (single-word y))

      :else
      (recur
        (mod y 10)
        (conj coll (single-word (- y (mod y 10))))))))


(defn- number- [x]
  (string/join "-" (single-word-coll x)))


(defn number [x]
  (if (and (< -1 x) (< x 1e12))
    (number- x)
    (throw (IllegalArgumentException.))))
