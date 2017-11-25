(ns meetup
  (:import
   [java.time.temporal TemporalAdjusters]
   [java.time DayOfWeek LocalDate]))


(defn day-by-dow [^Integer month ^Integer year]
  (let [dow             {DayOfWeek/MONDAY    :monday
                         DayOfWeek/TUESDAY   :tuesday
                         DayOfWeek/WEDNESDAY :wednesday
                         DayOfWeek/THURSDAY  :thursday
                         DayOfWeek/FRIDAY    :friday
                         DayOfWeek/SATURDAY  :saturday
                         DayOfWeek/SUNDAY    :sunday}
        dom->dow        #(-> (LocalDate/of year month ^Integer %1) (DayOfWeek/from) (dow))
        length-of-month (-> (LocalDate/of year month 1) (.lengthOfMonth))]
    (->> (inc length-of-month)
      (range 1)
      (map #(hash-map :day-of-week (dom->dow %1) :day-of-month %1))
      (group-by :day-of-week))))


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
  (-> (day-by-dow month year)
    (get day-of-week)
    (nth-day desc)
    (get :day-of-month)
    (list)
    (conj month year)))
