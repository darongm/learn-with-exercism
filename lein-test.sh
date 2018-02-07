#!/usr/bin/env bash

for d in `find $(pwd)/clojure/ -mindepth 1 -maxdepth 1 -type d`; do
  cd $d
  echo $(pwd)
  lein test || exit 1
done

