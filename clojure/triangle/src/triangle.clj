(ns triangle)


(defn type [a b c]
  (let [unique-side-length (count (hash-set a b c))
        illogical?         (or
                             (> 0 a b c)
                             (>= a (+ b c))
                             (>= b (+ a c))
                             (>= c (+ a b)))]
    (cond
      illogical? :illogical
      (= unique-side-length 1) :equilateral
      (= unique-side-length 2) :isosceles
      :else :scalene)))

