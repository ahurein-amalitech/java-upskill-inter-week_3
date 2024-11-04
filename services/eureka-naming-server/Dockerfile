# First stage: Build the JAR file
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DSkipTests

# Second stage: Create layers from the JAR
FROM eclipse-temurin:21-jdk-alpine AS builder
ARG JAR_FILE=/app/target/*.jar
#WORKDIR /app
#COPY --from ${JAR_FILE} application.jar

COPY --from=build /app/target/*.jar application.jar

RUN java -Djarmode=layertools -jar application.jar extract

# Third stage: Copy layers to final image
FROM eclipse-temurin:21-jdk-alpine
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
EXPOSE 8082
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
