(ns phone-number
  [:require [clojure.string :as string]])


(defn nanp-number [digits]
  (let [[ac1 ac2 ac3 ec1 ec2 ec3 & sn] digits]
    [[ac1 ac2 ac3] [ec1 ec2 ec3] sn]))


(defn ->nanp-number [coll]
  (let [digit? (fn [^Character c] (Character/isDigit c))
        digits (filter digit? coll)]
    (cond
      (= 10 (count digits)) (nanp-number digits)
      (= [11 \1] [(count digits) (first digits)]) (nanp-number (rest digits))
      :else [[\0 \0 \0] [\0 \0 \0] [\0 \0 \0 \0]])))


(defn number [coll]
  (->> coll
    (->nanp-number)
    (flatten)
    (string/join)))


(defn area-code [coll]
  (let [[area-code _ _] (->nanp-number coll)]
    (string/join area-code)))


(defn pretty-print [coll]
  (let [[area-code exchange-code subscriber-number] (->nanp-number coll)]
    (str
      "(" (string/join area-code) ") "
      (string/join exchange-code)
      "-"
      (string/join subscriber-number))))
