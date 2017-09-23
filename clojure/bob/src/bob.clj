(ns bob
  (:require
   [clojure.string :as string]))

(defn letter? [^Character c]
  (Character/isLetter c))

(defn upper-case? [^Character c]
  (Character/isUpperCase c))

(defn response-for [param]
  (let [yell?     (some->> param
                    (filter letter?)
                    (seq) ; this is required to work with some->>, since (every? true? []) always return true.
                    (every? upper-case?))
        question? (string/ends-with? param "?")
        silence?  (->> param
                    (string/trim)
                    (empty?))]
    (cond
      silence? "Fine. Be that way!"
      yell? "Whoa, chill out!"
      question? "Sure."
      :else "Whatever.")))
