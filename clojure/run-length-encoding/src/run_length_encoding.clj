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


(defn decode [acc ^Character ch]
  (let [ch-count #(if (empty? (:digits %1)) 1 (Integer/parseInt (:digits %1)))]
    (cond
      (Character/isDigit ch) (update acc :digits str ch)
      :otherwise (-> acc
                   (assoc :digits "")
                   (update :ret concat (repeat (ch-count acc) ch))))))

(defn run-length-decode
  "decodes a run-length-encoded string"
  [s]
  (let [init-state {:ret [] :digits ""}]
    (->> s
      (reduce decode init-state)
      (:ret)
      (string/join))))
