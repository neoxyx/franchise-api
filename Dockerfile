FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/franchise-api.jar franchise-api.jar
ENTRYPOINT ["java", "-jar", "/franchise-api.jar"]
