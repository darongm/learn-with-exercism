(defproject say "0.1.0-SNAPSHOT"
  :description "say exercise."
  :url "https://github.com/exercism/clojure/tree/master/exercises/say"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/test.check "0.9.0"]]
  :profiles {:dev {:plugins [[lein-eftest "0.4.3"]
                             [lein-auto "0.1.3"]]}})
