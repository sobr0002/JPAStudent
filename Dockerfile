# Multi-stage Dockerfile for a Java Maven application

# Stage 1: Build

# Bruger Maven image til at bygge app
FROM maven:3.9.9-eclipse-temurin-21 AS build

# Definerer containerens arbejdsmappe
WORKDIR /app

# Kopier pom.xml og download afhaengigheder
COPY pom.xml .
RUN mvn dependency:resolve # cacher dependencies

# Kopier kildekoden ind i containeren
COPY src ./src

# Bygger app og opretter jar.fil
RUN mvn clean package -DskipTests

# ----- Stage 2: Runtime - Run the application -----

# Bruger et letvaegt Java 21 runtime-image fra linux alpine
FROM eclipse-temurin:21-jdk-alpine

# Definerer arbejdsmappe for runtime containeren
WORKDIR /app

# Kopierer faerdige jar-fil fra build stage
COPY --from=build /app/target/*.jar app.jar

# Kommando starter app naar containeren startes
ENTRYPOINT ["java", "-jar", "app.jar"]