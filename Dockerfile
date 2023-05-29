# Use a base image with Java and Maven installed
FROM maven:3.8.6-eclipse-temurin-17 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml file
COPY pom.xml .

# Download the project dependencies
RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Build the application
RUN mvn package

# Create a new image with only the Java runtime
FROM eclipse-temurin:17.0.7_7-jre-alpine

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/cli-sudoku-1.0-SNAPSHOT.jar ./cli-sudoku.jar

# Create a volume mount point
VOLUME /app/src/main/resources/fileStore/

# Start the console application
CMD ["java", "-jar", "cli-sudoku.jar"]
