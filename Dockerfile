FROM openjdk:11-jre-slim
COPY target/PowerLegder-0.0.1-SNAPSHOT.jar PowerLegder-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","PowerLegder-0.0.1-SNAPSHOT.jar"]