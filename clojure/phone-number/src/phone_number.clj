(ns phone-number)


(defn area-code [state digit acc]
  (-> acc
    (assoc :state state)
    (update-in [:result :area-code] conj digit)))

(defn exchange-code [state digit acc]
  (-> acc
    (assoc :state state)
    (update-in [:result :exchange-code] conj digit)))

(defn subscriber-number [state digit acc]
  (-> acc
    (assoc :state state)
    (update-in [:result :subscriber-number] conj digit)))


(def transitions
  {[::initial 1]                (partial area-code ::area-code-1-11 1)
   [::area-code-1-11 2]         (partial area-code ::area-code-2-11 2)
   [::area-code-2-11 3]         (partial area-code ::area-code-3-11 3)
   [::area-code-3-11 4]         (partial exchange-code ::exchange-code-1-11 4)
   [::exchange-code-1-11 5]     (partial exchange-code ::exchange-code-2-11 5)
   [::exchange-code-2-11 6]     (partial exchange-code ::exchange-code-3-11 6)
   [::exchange-code-3-11 7]     (partial subscriber-number ::subscriber-number-1-11 7)
   [::subscriber-number-1-11 8] (partial subscriber-number ::subscriber-number-2-11 8)
   [::subscriber-number-2-11 9] (partial subscriber-number ::subscriber-number-3-11 9)
   [::subscriber-number-3-11 0] (partial subscriber-number ::subscriber-number-4-11 0)})


(defn nanp-state-machine [coll]
  (let [m  {:result {:area-code [] :exchange-code [] :subscriber-number []}
            :state  ::initial}
        sm (fn [{:keys [state result] :as acc} ^Character char]
             (let [digit         (Character/digit char 10)
                   transition-fn (get transitions [state digit] identity)]
               (transition-fn acc)))]
    (reduce sm m coll)))


(defn number [coll]
  (let [{:keys [state result]} (nanp-state-machine coll)
        {:keys [area-code exchange-code subscriber-number]} result]
    (if (= ::subscriber-number-4-11 state)
      (str
        (clojure.string/join area-code)
        (clojure.string/join exchange-code)
        (clojure.string/join subscriber-number))
      "0000000000")))

(comment
  (number "(123) 456-7890"))

(defn area-code [coll])


(defn pretty-print [coll])


