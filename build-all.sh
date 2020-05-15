#!/bin/bash
clear
cd dc-service
source build.sh
cd ../query-service
source build.sh
cd ../rt-service
source build.sh
cd ../mes-test-data
source build.sh

