FROM eclipse-temurin:25-jdk AS build
ARG MODULE
WORKDIR /workspace
COPY . .
RUN ./gradlew --no-daemon ":${MODULE}:bootJar" -x test

FROM eclipse-temurin:25-jre
ARG MODULE
WORKDIR /app
COPY --from=build /workspace/${MODULE}/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]