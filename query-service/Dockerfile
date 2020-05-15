FROM amazoncorretto:8
RUN mkdir /opt/app
WORKDIR /opt/app
COPY bin/mes-query-0.0.1-SNAPSHOT.jar .
COPY bin/wait-for-it.sh .
ENTRYPOINT ["java","-jar","mes-query-0.0.1-SNAPSHOT.jar"]

