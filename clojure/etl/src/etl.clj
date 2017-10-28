(ns etl)


(defn transform [m]
  (let [f (fn [acc [k coll]]
            (reduce #(assoc %1 (clojure.string/lower-case %2) k) acc coll))]
    (reduce f {} m)))
