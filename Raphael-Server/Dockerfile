FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/Raphael-Server-0.0.1-SNAPSHOT.jar /app/Raphael-Server-0.0.1-SNAPSHOT.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/app/Raphael-Server-0.0.1-SNAPSHOT.jar"]