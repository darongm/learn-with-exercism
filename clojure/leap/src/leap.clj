(ns leap)


(defn leap-year? [n]
  (let [db4   (= 0 (mod n 4))
        db100 (= 0 (mod n 100))
        db400 (= 0 (mod n 400))]
    (or db400 (and db4 (not db100)))))
