FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/Edufy-Service-0.0.1-SNAPSHOT.jar /app/Edufy-Service-0.0.1-SNAPSHOT.jar

EXPOSE 6565

ENTRYPOINT ["java", "-jar", "/app/Edufy-Service-0.0.1-SNAPSHOT.jar"]