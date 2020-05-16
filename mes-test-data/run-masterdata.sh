#!/bin/bash
clear
docker run --rm djad/mes-test-data com.djad.mestestdata.scenario.MasterDataScenario http://localhost:8082
