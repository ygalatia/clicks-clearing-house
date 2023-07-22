FROM gradle:8.0.2-jdk17-alpine AS build-clearing
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build --no-daemon

FROM eclipse-temurin:17-jdk-alpine

EXPOSE 8080

RUN mkdir /app

COPY --from=build-clearing /home/gradle/src/build/libs/*.jar /app/clearing-service.jar

ENTRYPOINT ["java", "-jar", "app/clearing-service.jar"]