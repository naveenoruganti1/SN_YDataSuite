# Use a lightweight JDK 21 base image (significantly smaller than Ubuntu)
FROM eclipse-temurin:21-jre-alpine

# Set working directory
WORKDIR /datasuite/yaml

# Copy jar file from build directory into new created directory
COPY build/libs/SN_YDataSuite-1.0.jar .

CMD ["java", "-jar", "SN_YDataSuite-1.0.jar"]