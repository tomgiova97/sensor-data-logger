# Use an OpenJDK runtime with Java 17 as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the .env file into the container
#COPY .env .env

# Copy the JAR file built by Maven into the container
COPY target/sensor-data-logger-0.0.1-SNAPSHOT.jar sensor-data-logger-0.0.1-SNAPSHOT.jar

# Specify environment variables
#ENV SPRING_CONFIG_LOCATION=file:/app/env.properties

# Expose the port that your Spring Boot application will run on (if needed)
EXPOSE 8080

# Command to run your application
CMD ["sh", "-c", "java -jar sensor-data-logger-0.0.1-SNAPSHOT.jar"]
