FROM openjdk:11.0-jdk-slim

EXPOSE 8080
EXPOSE 8000

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar catholicon-ms-matchcard.jar"]

COPY target/catholicon-ms-matchcard.jar catholicon-ms-matchcard.jar