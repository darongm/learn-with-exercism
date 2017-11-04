(ns run-length-encoding
  (:require
   [clojure.string :as string]
   [clojure.set :as sets]))


(defn run-length-encode
  "encodes a string with run-length-encoding"
  [s]
  (let [ret (partition-by identity s)
        ret (map frequencies ret)
        ret (map sets/map-invert ret)
        ret (mapcat vec ret)
        ret (reduce concat ret)
        ret (remove #(= 1 %) ret)
        ret (string/join ret)]
    ret))


(defn run-length-decode
  "decodes a run-length-encoded string"
  [s])
