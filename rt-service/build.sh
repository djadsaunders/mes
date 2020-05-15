#!/bin/bash
rm bin/*.jar
mkdir bin
mvn clean package
cp target/rt-service-0.0.1-SNAPSHOT.jar bin
