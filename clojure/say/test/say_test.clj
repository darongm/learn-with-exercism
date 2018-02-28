(ns say-test
  (:require
    [clojure.test :refer [deftest is]]
    [clojure.test.check.clojure-test :refer [defspec]]
    [clojure.test.check.generators :as gen]
    [clojure.test.check.properties :as prop]
    [clojure.string :as string]
    say))


(def gen-valid-number (gen/large-integer* {:min 0 :max (dec' 1e12)}))


(def zero-to-twenty
  #{"zero"
    "one"
    "two"
    "three"
    "four"
    "five"
    "six"
    "seven"
    "eight"
    "nine"
    "ten"
    "eleven"
    "twelve"
    "thirteen"
    "fourteen"
    "fifteen"
    "sixteen"
    "seventeen"
    "eighteen"
    "nineteen"
    "twenty"})


(defspec prop-should-not-blow-up-for-every-valid-input 100
  (prop/for-all [n gen-valid-number]
    (say/number n)))


(defspec prop-end-with-one-of-word-between-zero-to-twenty 100
  (prop/for-all [n gen-valid-number]
    (let [word (say/number n)]
      (some #(string/ends-with? word %) zero-to-twenty))))


(defspec prop-twenty 100
  (prop/for-all [n (gen/large-integer* {:min 21 :max (dec' 30)})]
    (re-find #"twenty-" (say/number n))))


(deftest zero-test
  (is (= "zero" (say/number 0))))

(deftest one-test
  (is (= "one" (say/number 1))))

(deftest fourteen-test
  (is (= "fourteen" (say/number 14))))

(deftest twenty-test
  (is (= "twenty" (say/number 20))))

(deftest twenty-two-test
  (is (= "twenty-two" (say/number 22))))

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
