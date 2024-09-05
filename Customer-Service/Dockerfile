FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/Customer-Service-0.0.1-SNAPSHOT.jar /app/Customer-Service-0.0.1-SNAPSHOT.jar

EXPOSE 6060

ENTRYPOINT ["java", "-jar", "/app/Customer-Service-0.0.1-SNAPSHOT.jar"]