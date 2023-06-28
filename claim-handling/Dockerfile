FROM gradle:8.0.2-jdk17-alpine AS build-claimhandling
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build --no-daemon

FROM eclipse-temurin:17-jdk-alpine

EXPOSE 8080

RUN mkdir /app

COPY --from=build-claimhandling /home/gradle/src/build/libs/*.jar /app/claimhandling-service.jar

ENTRYPOINT ["java", "-jar", "app/claimhandling-service.jar"]