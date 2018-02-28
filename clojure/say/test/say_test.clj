(ns say-test
  (:require
    [clojure.test :refer [deftest is]]
    [clojure.test.check.clojure-test :refer [defspec]]
    [clojure.test.check.generators :as gen]
    [clojure.test.check.properties :as prop]
    say))


(def gen-valid-number (gen/large-integer* {:min 0 :max (- 1e12 1)}))


(def zero-to-twenty
  {0  "zero"
   1  "one"
   2  "two"
   3  "three"
   4  "four"
   5  "five"
   6  "six"
   7  "seven"
   8  "eight"
   9  "nine"
   10 "ten"
   11 "eleven"
   12 "twelve"
   13 "thirteen"
   14 "fourteen"
   15 "fifteen"
   16 "sixteen"
   17 "seventeen"
   18 "eighteen"
   19 "nineteen"
   20 "twenty"})


(def gen-zero-to-twenty (gen/elements zero-to-twenty))


(defspec prop-unique-word-one-to-twenty 100
  (prop/for-all [[n word] gen-zero-to-twenty]
    (= word (say/number n))))


(deftest zero-test
  (is (= "zero" (say/number 0))))

(deftest one-test
  (is (= "one" (say/number 1))))

(deftest fourteen-test
  (is (= "fourteen" (say/number 14))))

(deftest twenty-test
  (is (= "twenty" (say/number 20))))

;(deftest twenty-two-test
;  (is (= "twenty-two" (say/number 22))))
;
;(deftest one-hundred-test
;  (is (= "one hundred" (say/number 100))))
;
;(deftest one-hundred-twenty-three-test
;  (is (= "one hundred twenty-three" (say/number 123))))
;
;(deftest one-thousand-test
;  (is (= "one thousand" (say/number 1000))))
;
;(deftest one-thousand-two-hundred-thirty-four-test
;  (is (= "one thousand two hundred thirty-four" (say/number 1234))))
;
;(deftest one-million-test
;  (is (= "one million" (say/number 1000000))))
;
;(deftest one-million-two-thousand-three-hundred-forty-five-test
;  (is (= "one million two thousand three hundred forty-five" (say/number 1002345))))
;
;(deftest one-billion-test
;  (is (= "one billion" (say/number 1000000000))))
;
;(deftest a-big-number-test
;  (is (= "nine hundred eighty-seven billion six hundred fifty-four million three hundred twenty-one thousand one hundred twenty-three" (say/number 987654321123))))

(deftest below-zero-is-out-of-range-test
  (is (thrown? IllegalArgumentException (say/number -1))))

(deftest numbers-above-999999999999-out-of-range-test
  (is (thrown? IllegalArgumentException (say/number 1000000000000))))
