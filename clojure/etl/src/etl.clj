(ns etl
  (:require
   [clojure.string :as string]
   [clojure.set :as sets]))


;;; inspired by http://exercism.io/submissions/869dfbc792d945b2bec746daa78568b3
(defn transform [m]
  (let [new-score (for [[score words] m
                        word words
                        :let [lw (string/lower-case word)]]
                    [lw score])]
    (into {} new-score)))
