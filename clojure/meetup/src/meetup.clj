(ns meetup
  (:import
   [java.time.temporal TemporalAdjusters]
   [java.time DayOfWeek LocalDate]))


(defn day [^Integer month ^Integer year ^Integer dom]
  (let [dow      {DayOfWeek/MONDAY    :monday
                  DayOfWeek/TUESDAY   :tuesday
                  DayOfWeek/WEDNESDAY :wednesday
                  DayOfWeek/THURSDAY  :thursday
                  DayOfWeek/FRIDAY    :friday
                  DayOfWeek/SATURDAY  :saturday
                  DayOfWeek/SUNDAY    :sunday}
        dom->dow (-> (LocalDate/of year month dom) (DayOfWeek/from) (dow))]
    {:day-of-week dom->dow :day-of-month dom}))


(defn day-seq [^Integer month ^Integer year]
  (let [length-of-month (-> (LocalDate/of year month 1) (.lengthOfMonth))]
    (->> length-of-month
      (inc)
      (range 1)
      (map #(day month year %1)))))


(defn nth-day [day-coll desc]
  (case desc
    :first (first day-coll)
    :second (second day-coll)
    :third (nth day-coll 2)
    :fourth (nth day-coll 3)
    :last (last day-coll)
    :teenth (->> day-coll
              (filter #(<= 13 (:day-of-month %1)))
              (first))))


(defn meetup [month year day-of-week desc]
  (let [group-by-dow #(group-by :day-of-week %1)]
    (-> (day-seq month year)
      (group-by-dow)
      (get day-of-week)
      (nth-day desc)
      (get :day-of-month)
      (list)
      (conj month year))))
