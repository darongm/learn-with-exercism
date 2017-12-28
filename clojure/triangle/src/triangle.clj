(ns triangle
  (:refer-clojure :exclude [type]))


(defn type [a b c]
  (let [unique-side-length (count (hash-set a b c))
        [side-1 side-2 side-3 :as sides] (sort [a b c])
        two-less-than-one? (>= side-3 (+ side-1 side-2))
        illogical?         (or
                             two-less-than-one?
                             (some neg? sides))]
    (cond
      illogical? :illogical
      (= unique-side-length 1) :equilateral
      (= unique-side-length 2) :isosceles
      :else :scalene)))

