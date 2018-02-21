(ns reverse-string
  (:require
    [clojure.string :as string]))


(defn reverse-string [st]
  (->> (seq st) (reduce conj '()) (string/join)))


