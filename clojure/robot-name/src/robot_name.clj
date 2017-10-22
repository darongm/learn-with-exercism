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


;;; Example value: {1 "IT783" 2 "TL408"}
;;;
;;; By model `robot` as ID, we can use it as a pointer to its name,
;;; keep the robot immutable, and reduced the number of states we have to maintain.
(def *robot-coll (atom {}))


(defn assoc-random-name
  ([coll]
   ; create new robot
   (assoc-random-name coll (inc (count coll))))
  ([coll robot]
   ; update existing robot
   (let [used-names (set (vals coll))
         new-name   (->>
                      (repeatedly random-robot-name)
                      (remove used-names)
                      (first))]
     (assoc coll robot new-name))))


(defn robot []
  (->
    (swap! *robot-coll assoc-random-name)
    ; swap! returned value that was swapped in, thus no race condition.
    (count)))


(defn robot-name [robot] (@*robot-coll robot))


(defn reset-name [robot]
  (swap! *robot-coll assoc-random-name robot))
