#!/usr/bin/env bash

for d in `find $(pwd)/clojure/ -type d -mindepth 1 -maxdepth 1`; do
  cd $d
  echo $(pwd)
  lein deps
done
