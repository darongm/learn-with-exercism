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
