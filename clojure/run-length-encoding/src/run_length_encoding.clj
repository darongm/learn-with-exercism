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


(defn decode [[state digits ret] ^Character ch]
  (let [digit         true
        not-digit     false
        transition-fn {:state/empty {digit     (fn [c] [:state/digit (str c) ret])
                                     not-digit (fn [c] [:state/empty "1" (concat ret (repeat (Integer/parseInt digits) c))])}
                       :state/digit {digit     (fn [c] [:state/digit (str digits c) ret])
                                     not-digit (fn [c] [:state/empty "1" (concat ret (repeat (Integer/parseInt digits) c))])}}
        f             (get-in transition-fn [state (Character/isDigit ch)] (constantly [:state/unknown digits ret]))]
    (f ch)))

(defn run-length-decode
  "decodes a run-length-encoded string"
  [s]
  (let [ret (reduce decode [:state/empty "1" []] s)
        ret (get ret 2)
        ret (string/join ret)]
    ret))
