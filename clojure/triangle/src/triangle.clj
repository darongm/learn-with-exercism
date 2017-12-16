(ns triangle)


(defn type [a b c]
  (let [unique-count (count (hash-set a b c))]
    (cond
      (or
        (> 0 a b c)
        (>= a (+ b c))
        (>= b (+ a c))
        (>= c (+ a b)))
      :illogical

      (= unique-count 1) :equilateral

      (= unique-count 2) :isosceles

      :else :scalene)))

