#!/bin/sh

function runTest(){
  echo 'Running K6 tests... '
  k6 run script.js
}

runTest