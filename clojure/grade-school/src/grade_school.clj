(ns grade-school)


(comment
  (defn add [db student-name student-grade]
    (let [conj+ (fnil conj [])]
      (update-in db [student-grade] conj+ student-name))))


;; inspired by http://exercism.io/submissions/4a3c941574f04eebbe5982481c0ed127
;; and http://clojuredocs.org/clojure.core/merge-with#example-5653839de4b0be225c0c4799
(defn add [db student-name student-grade]
  (merge-with into db {student-grade [student-name]}))


(defn grade [db grade]
  (get db grade []))


;; inspired by http://exercism.io/submissions/837c8fb14cb6466bb3d18fafc87b21b5
;; to preserve vector after sort
(defn sorted [db]
  (let [sort-v       (fn [v] (into [] (sort v)))
        sorted-names (fn [[g names]] [g (sort-v names)])]
    (->> db
      (map sorted-names)
      (into (sorted-map-by <)))))
