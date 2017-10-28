(ns etl)


(defn transform [m]
  (let [f (fn [acc [k coll]]
            (reduce #(assoc %1 (clojure.string/lower-case %2) k) acc coll))]
    (->> m
      (map (fn [[score letter-coll]] (zipmap letter-coll (repeat score))))
      (reduce merge {})
      (reduce-kv #(assoc %1 (clojure.string/lower-case %2) %3) {}))))


