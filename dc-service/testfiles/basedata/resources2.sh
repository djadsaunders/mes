#!/bin/bash
curl -d "{\"tag\":\"LINEB\", \"name\":\"Line B\"}" -H $MES_HEADER $MES_URI_PREFIX/resource | jq
