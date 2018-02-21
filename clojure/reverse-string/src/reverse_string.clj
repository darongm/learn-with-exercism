(ns reverse-string
  (:require
    [clojure.string :as string]))


(defn reverse-string [st]
  (->> st (reduce conj '()) (string/join)))


