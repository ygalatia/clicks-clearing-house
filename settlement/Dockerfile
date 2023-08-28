FROM gradle:8.0.2-jdk17-alpine AS build-settlement
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build --no-daemon

FROM eclipse-temurin:17-jdk-alpine

EXPOSE 8080

RUN mkdir /app

COPY --from=build-settlement /home/gradle/src/build/libs/*.jar /app/settlement-service.jar

ENTRYPOINT ["java", "-jar", "app/settlement-service.jar"]