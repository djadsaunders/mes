FROM amazoncorretto:8
RUN mkdir /opt/app
WORKDIR /opt/app
COPY bin/*.jar .
ENTRYPOINT ["java","-jar","mes-test-data-0.0.1-SNAPSHOT.jar"]
