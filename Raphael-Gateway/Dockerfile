FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/Raphael-Gateway-0.0.1-SNAPSHOT.jar /app/Raphael-Gateway-0.0.1-SNAPSHOT.jar

EXPOSE 8585

ENTRYPOINT ["java", "-jar", "/app/Raphael-Gateway-0.0.1-SNAPSHOT.jar"]