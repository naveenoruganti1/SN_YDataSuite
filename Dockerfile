
# Operating System
FROM ubuntu:latest

# Update Oeration System
RUN apt update -y

# Install JDK-21
RUN apt install -y openjdk-21-jdk

# Create a Directory
RUN mkdir -p datasuite/yaml

WORKDIR /datasuite/yaml

# Copy jar file from build directory into new created directory
COPY build/libs/SN_YDataSuite-1.0.jar .

EXPOSE 8091

CMD ["java", "-jar", "SN_YDataSuite-1.0.jar"]