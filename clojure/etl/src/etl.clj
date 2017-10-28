(ns etl
  (:require
   [clojure.string :as string]
   [clojure.set :as sets]))


(defn transform [m]
  (let [lower-case-key (fn [m k-coll v]
                         (as-> k-coll k
                           (map string/lower-case k)
                           (assoc m k v)))
        expand-map     (fn [m k-coll v]
                         (-> k-coll
                           (zipmap (repeat v))
                           (merge m)))]
    (->> m
      (sets/map-invert)
      (reduce-kv lower-case-key {})
      (reduce-kv expand-map {}))))
