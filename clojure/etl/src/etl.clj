(ns etl
  (:require
   [clojure.string :as string]
   [clojure.set :as sets]))


(defn transform [m]
  (let [lower-case-key (fn [m k-coll v]
                         (assoc m (map string/lower-case k-coll) v))
        expand-map     (fn [m k-coll v]
                         (merge m (zipmap k-coll (repeat v))))]
    (->> m
      (sets/map-invert)
      (reduce-kv lower-case-key {})
      (reduce-kv expand-map {}))))
