(ns grade-school)


(defn add [db student-name student-grade]
  (let [conj+ (fnil conj [])]
    (update-in db [student-grade] conj+ student-name)))


(defn grade [db grade]
  (get db grade []))


(defn sorted [db])
