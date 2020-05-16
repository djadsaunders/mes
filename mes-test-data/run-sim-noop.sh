#!/bin/bash
clear
docker run --rm djad/mes-test-data com.djad.mestestdata.scenario.SimulationScenario http://localhost:8082 NOOP
