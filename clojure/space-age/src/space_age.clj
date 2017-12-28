(ns space-age)

(defn on-earth [n]
  (/ n 31557600))

(defn on-mercury [n]
  (/ (on-earth n) 0.2408467))

(defn on-venus [n]
  (/ (on-earth n) 0.61519726))

(defn on-mars [n]
  (/ (on-earth n) 1.8808158))

(defn on-jupiter [n]
  (/ (on-earth n) 11.862615))

(defn on-saturn [n]
  (/ (on-earth n) 29.447498))

(defn on-uranus [n]
  (/ (on-earth n) 84.016846))

(defn on-neptune [n]
  (/ (on-earth n) 164.79132))

;; more elegant solution
;; from http://exercism.io/submissions/18f6d12ebd2b48108d8dcffbf2688ab7
(comment
  (def earth-year 31557600)

  (defn on-earth [sec]
    (/ sec earth-year))

  (defn- relative-to-earth [scale sec]
    (/ (on-earth sec) scale))

  (def on-mercury (partial relative-to-earth 0.2408465))
  (def on-venus (partial relative-to-earth 0.61519726))
  (def on-mars (partial relative-to-earth 1.8808158))
  (def on-jupiter (partial relative-to-earth 11.862615))
  (def on-saturn (partial relative-to-earth 29.447498))
  (def on-uranus (partial relative-to-earth 84.016846))
  (def on-neptune (partial relative-to-earth 164.79132)))
