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


(defn decode-stm [[state multiplier ret] ^Character ch]
  (let [reset-multiplier  str
        append-ret        #(concat ret [%1])
        expand-ret        #(concat ret (repeat (Integer/parseInt multiplier) %1))
        append-multiplier #(str multiplier %1)
        digit             true
        not-digit         false
        stm               {:state/wait-for-digit {digit     (fn [d]
                                                              [:state/wait-for-char (reset-multiplier d) ret])
                                                  not-digit (fn [c]
                                                              [:state/wait-for-digit nil (append-ret c)])}
                           :state/wait-for-char  {digit     (fn [d]
                                                              [:state/wait-for-char (append-multiplier d) ret])
                                                  not-digit (fn [c]
                                                              [:state/wait-for-digit nil (expand-ret c)])}}
        digit?            (Character/isDigit ch)
        transition        (get-in
                            stm
                            [state digit?]
                            (constantly [:state/unknown multiplier ret]))]
    (transition ch)))

(defn run-length-decode
  "decodes a run-length-encoded string"
  [s]
  (let [[_ _ ret] (reduce decode-stm [:state/wait-for-digit nil []] s)]
    (string/join ret)))
