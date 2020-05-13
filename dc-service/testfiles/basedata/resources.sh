#!/bin/bash
curl -d "{\"tag\":\"LINEA\", \"name\":\"Line A\"}" -H $MES_HEADER $MES_URI_PREFIX/resource | jq
