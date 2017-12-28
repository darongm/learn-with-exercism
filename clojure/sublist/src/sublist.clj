(ns sublist)


(defn sublist? [a-coll b-coll]
  (let [candidates (partition (count a-coll) 1 b-coll)]
    (some #{a-coll} candidates)))


(defn classify [a-coll b-coll]
  (cond
    (= a-coll b-coll) :equal
    (sublist? a-coll b-coll) :sublist
    (sublist? b-coll a-coll) :superlist
    :else :unequal))
