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


(def *robot-coll (ref []))


(defn random-robot! [*robot]
  (let [f (fn [coll]
            (->> (repeatedly random-robot-name)
              (remove #(contains? coll %))
              (first)
              (conj coll)))]
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
