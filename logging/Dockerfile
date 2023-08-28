FROM gradle:8.0.2-jdk17-alpine AS build-logging
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build --no-daemon

FROM eclipse-temurin:17-jdk-alpine

EXPOSE 8080

RUN mkdir /app

COPY --from=build-logging /home/gradle/src/build/libs/*.jar /app/logging-service.jar

ENTRYPOINT ["java", "-jar", "app/logging-service.jar"]