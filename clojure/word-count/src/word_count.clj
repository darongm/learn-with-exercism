(ns word-count
  (:require
   [clojure.string :as string]))


(defn word-count [words]
  (->> words
    (string/lower-case)
    (re-seq #"\w+")
    (frequencies)))
