FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim

WORKDIR /app
COPY --from=build /app/target/sgcc-agendamento-1.00.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
