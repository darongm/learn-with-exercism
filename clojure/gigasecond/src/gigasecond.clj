(ns gigasecond
  (:import
   [java.time LocalDateTime]))


(defn from [^Integer year ^Integer month ^Integer day]
  (let [dt (LocalDateTime/of year month day 0 0 0)
        dt (.plusSeconds dt 1e9)]
    [(.getYear dt)
     (.getMonthValue dt)
     (.getDayOfMonth dt)]))
