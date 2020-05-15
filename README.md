# MES Experimental Project

This project uses the MES domain to experiment with various technologies, including Microservices, SpringBoot, Node.JS, Vue, Angular 2, Kafka, Docker.

MES (Manufacturing Execution Systems) sit just above the machines and low level systems in manufacturing environments. They collect data from machines and make use of it to improve performance, reduce waste etc. They may also control the machinery directly.

# Modules

This project uses the following modules. The aim is to treat them as microservices which could (with some changes) be parallelized to scale up. Mostly these services can be run in Docker containers (see below)

## dc-service

Data collection service which receives signals from machines (e.g. state changes) and posts them to an event stream.

This is implemented as a `SpringBoot` application. `Kafka` is used as the event stream.

## rt-service

One way signals can be used is to process them and pass them to a datastore. In this case, an RDMBS is used to represent the current state of the system.

The rt-service is implemented as a SpringBoot app which subscribes to Kafka events and writes to a MySQL database.

## query-service

SpringBoot app which provides a REST interface to the real-time datastore to support queries

## mes-test-data

Basic Java app which populates test or demo data according to pre-defined scenarios

Use the following scripts:

* run-masterdata: populate base data only
* run-sim: a continually running simulation of changing data

## query-service-nodejs (recommended)

Version of the SpringBoot query service in Node.JS, providing the same functionality

## ui-vue

Basic UI using Vue

## ui-angular (recommended)

More sophisticated UI using Angular 2

# Compile and Run

The SpringBoot applications are Maven projects and can be built on the command line with the usual "mvn clean package" command. Most projects have convenience scripts to compile and/or run each app.

To run the whole project using the Angular UI, it's easiest to use docker-compose (see below) to start the core services. Then:

* Go to mes-test-data and run ./run-sim to start running in some similation data
* In another window, go to ui-angular and run "ng serve"
* Navigate to "localhost:4200", a dashboard should appear

If you use the Node.JS query service instead of the SpringBoot app, I think the Angular UI should work just the same (although you will need to make sure the SpringBoot service is not started by Docker)

# Docker

The core services can easily be run using Docker Compose. Call: "docker-compose up -d".

This will create the following containers:

* Zookeeper (required for Kafka)
* Kafka
* MySQL (for the real-time datastore)
* dc-service
* rt-service
* query-service

Note: you can use the Node.JS query service instead by running the Node.JS app. You could modify docker-compose.yml so that the SpringBoot query service container is not created

# To Do

* Try out Kafka Streams to stream events directly to real-time app. May require events to be processed and posted back to Kafka as summaries
* Get Node running in Docker so that the query service can be permanently implemented that way
* Host on AWS
* Experiment with AWS IOT services
* Add a rules services
* Add a history service for longer-term data storage and query
