(ns clock)


;; inspired by http://exercism.io/submissions/468867ee4cbd47ca83a4d850f3082e42
(defn time-in-minute [hour minute]
  (let [hour-in-mn (* 60 hour)
        all-in-mn  (+ hour-in-mn minute)
        all-in-mn  (mod all-in-mn (* 60 24))]
    all-in-mn))


(defn clock [hour minute]
  (let [time (time-in-minute hour minute)
        h    (quot time 60)
        mn   (mod time 60)]
    {:hour   h
     :minute mn}))


(defn clock->string [{:keys [hour minute]}]
  (format "%02d:%02d" hour minute))


(defn add-time [{:keys [hour minute]} minute2]
  (clock hour (+ minute minute2)))
