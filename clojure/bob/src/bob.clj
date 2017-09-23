(ns bob
  (:require
   [clojure.string :as string]))


(defn yell? [param]
  (let [letter?     (fn [^Character c] (Character/isLetter c))
        upper-case? (fn [^Character c] (Character/isUpperCase c))]
    (some->> param
      (filter letter?)
      (not-empty) ; this is required to work with some->>, since (every? true? []) always return true.
      (every? upper-case?))))


(defn question? [param]
  (string/ends-with? param "?"))


(defn silence? [param]
  (string/blank? param))


(defn response-for [param]
  (cond
    (silence? param) "Fine. Be that way!"
    (yell? param) "Whoa, chill out!"
    (question? param) "Sure."
    :else "Whatever."))
