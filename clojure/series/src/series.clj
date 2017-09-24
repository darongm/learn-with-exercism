(ns series)

(defn slices [string n]
  (->> string
    (partition n 1)
    (map clojure.string/join)
    (dedupe)))
