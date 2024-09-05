FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/Media-Service-0.0.1-SNAPSHOT.jar /app/Media-Service-0.0.1-SNAPSHOT.jar

EXPOSE 7070

ENTRYPOINT ["java", "-jar", "/app/Media-Service-0.0.1-SNAPSHOT.jar"]