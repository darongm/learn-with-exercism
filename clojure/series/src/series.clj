(ns series)


(defn slices- [string n]
  (->> string
    (partition n 1)
    (map clojure.string/join)))


(defn slices [string n]
  (if (= n 0)
    [""]
    (slices- string n)))
