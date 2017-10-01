(ns phone-number
  [:require [clojure.string :as string]])


(defn nanp-number [digits]
  (let [[ac1 ac2 ac3 ec1 ec2 ec3 sn1 sn2 sn3 sn4] digits]
    [(str ac1 ac2 ac3)
     (str ec1 ec2 ec3)
     (str sn1 sn2 sn3 sn4)]))


(defn ->nanp-number [coll]
  (let [digit? (fn [^Character c] (Character/isDigit c))
        digits (filter digit? coll)]
    (cond
      (= 10 (count digits))
      (nanp-number digits)

      (and (= 11 (count digits)) (= \1 (first digits)))
      (nanp-number (rest digits))

      :else ["000" "000" "0000"])))


(defn number [coll]
  (->> coll
    (->nanp-number)
    (string/join)))


(defn area-code [coll]
  (->> coll
    (->nanp-number)
    (first)))


(defn pretty-print [coll]
  (let [[area-code exchange-code subscriber-number] (->nanp-number coll)]
    (format "(%s) %s-%s" area-code exchange-code subscriber-number)))
