(ns reverse-string-test
  (:require
    [clojure.test :refer [deftest is]]
    [clojure.test.check :as tc]
    [clojure.test.check.generators :as gen]
    [clojure.test.check.properties :as prop]
    [clojure.test.check.clojure-test :refer [defspec]]
    reverse-string))

(deftest empty-string-test
  (is (= "" (reverse-string/reverse-string ""))))

(deftest a-word-test
  (is (= "tobor" (reverse-string/reverse-string "robot"))))

(deftest capitalised-word-test
  (is (= "nemaR" (reverse-string/reverse-string "Ramen"))))

(deftest sentence-with-punctuation-test
  (is (= "!yrgnuh m'I" (reverse-string/reverse-string "I'm hungry!"))))

(deftest palindrome-test
  (is (= "racecar" (reverse-string/reverse-string "racecar"))))


(defspec double-reverse-should-be-the-same 100
  (prop/for-all [s gen/string]
    (= s (reverse-string/reverse-string (reverse-string/reverse-string s)))))
