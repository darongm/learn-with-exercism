(ns robot-name
  (:require
   [clojure.string :as string]))


(defn random-robot-name []
  (let [asci-letters (range 65 91)
        asci-digits  (range 48 57)
        part1        (take 2 (shuffle asci-letters))
        part2        (take 3 (shuffle asci-digits))
        full-name    (map char (concat part1 part2))]
    (string/join full-name)))


;;; doc https://clojure.org/reference/refs
(def *robot-coll (ref []))


(defn random-robot! [*robot]
  (let [f (fn [coll]
            (->> (repeatedly random-robot-name)
              (remove #(contains? coll %))
              (first)
              (conj coll)))]
    ; If use `atom` like below, there's a race condition.
    ; Assuming the method called by 2 callers at the same time. The following can happen:
    ; Caller 1: (swap! *robot-coll f) -> ["123"]
    ; Caller 2: (swap! *robot-coll f) -> ["123" "456"]
    ; Caller 1: (last @*robot-coll)-> "456"
    ; Caller 2: (last @*robot-coll)-> "456"
    ; Which is wrong cause `Caller 1` expects "123" and the names are not unique.
    (comment
      (do
        (swap! *robot-coll f)
        (last @*robot-coll)))
    ; Using the semantics of `ref`, changes to `*robot-coll` and `*robot`
    ; are done in one transaction.
    ; The trade off is that `ref` reduce concurrency executions.
    (dosync
      (alter *robot-coll f)
      (ref-set *robot (last @*robot-coll)))))


(defn robot []
  (let [*r (ref nil)]
    (random-robot! *r)
    *r))


(defn robot-name [*robot] @*robot)


(defn reset-name [*robot]
  (random-robot! *robot))
