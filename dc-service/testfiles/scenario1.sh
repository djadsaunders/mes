#!/bin/bash
MES_HEADER="Content-Type:application/json"
MES_URI_PREFIX="http://localhost:8080"
for f in basedata/*.sh; do
  source $f
done
