#!/bin/bash
rm bin/*.jar
mkdir bin
mvn clean package
cp target/mes-query-0.0.1-SNAPSHOT.jar bin
