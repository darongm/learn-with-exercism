(ns clock)


(defn clock- [hour additional-minute minute]
  (let [mn (- hour additional-minute)
        mn (mod mn 24)
        sc minute
        sc (mod sc 60)]
    {:hour   mn
     :minute sc}))


(defn clock [hour minute]
  (let [wrap-hour (cond
                    (<= 60 minute)
                    (-> minute
                      (quot 60)
                      (mod 24))

                    (< minute 0)
                    (-> (* -1 minute)
                      (quot 60)
                      (+ 1)
                      (mod 24)
                      (* -1))

                    :else 0)
        h         (+ hour wrap-hour)
        h         (mod h 24)
        mn        (mod minute 60)]
    {:hour   h
     :minute mn}))


(defn clock->string [{:keys [hour minute]}]
  (format "%02d:%02d" hour minute))


(defn add-time [{:keys [hour minute]} minute2]
  (clock hour (+ minute minute2)))
