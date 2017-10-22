(ns robot-name
 (:require
  [clojure.string :as string]))


(defn random-robot []
  (let [asci-letters (range 65 91)
        asci-digits  (range 48 57)
        part1        (take 2 (shuffle asci-letters))
        part2        (take 3 (shuffle asci-digits))
        full-name    (map char (concat part1 part2))]
    (string/join full-name)))


(def *robot-coll (atom []))


(defn random-robot! []
   (let [f (fn [coll]
             (->> (repeatedly random-robot)
               (remove #(contains? coll %))
               (first)
               (conj coll)))]
     (do
       (swap! *robot-coll f)
       (last @*robot-coll))))


(defn robot []
  (atom (random-robot!)))


(defn robot-name [*robot] @*robot)


(defn reset-name [*robot]
  (reset! *robot (random-robot!)))
