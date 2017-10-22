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


(defn -robot
  ([]
   (random-robot))
  ([old-name]
   (->> (repeatedly random-robot)
     (remove #{old-name})
     (first))))


(defn robot []
  (atom (-robot)))


(defn robot-name [*robot] @*robot)


(defn reset-name [*robot]
  (swap! *robot -robot))
