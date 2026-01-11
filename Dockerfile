# Multi-stage Dockerfile for a Java Maven application

# Stage 1: Build
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:resolve # cacher dependencies

COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime - Run the application
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]