# 🔹 Stage 1: Build the Spring Boot application inside Alpine Linux
FROM openjdk:17 AS builder
WORKDIR /app

# Copy Maven project files and dependencies first (for caching)
COPY pom.xml .
COPY src ./src

# Build the JAR file inside the container
RUN mvn clean install

# 🔹 Stage 2: Create a lightweight runtime image with Alpine Linux
FROM openjdk:17
LABEL maintainer="ankanroychowdhury"

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Use a non-root user for security
#RUN addgroup -S testuser && adduser -S testuser -G testuser
#USER testuser

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]