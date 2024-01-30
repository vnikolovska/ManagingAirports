FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
COPY target/ManagingAirports-0.0.1-SNAPSHOT.jar airports.jar
ENTRYPOINT ["java","-jar","/airports.jar"]