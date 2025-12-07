FROM openjdk:21-slim

WORKDIR /app

# Copy built JAR
COPY target/Eureka-Restaurant-Registry-Service-0.0.1-SNAPSHOT.jar app.jar

# Create non-root user for security
RUN groupadd -r eureka && useradd -r -g eureka eureka
USER eureka

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "app.jar"]
