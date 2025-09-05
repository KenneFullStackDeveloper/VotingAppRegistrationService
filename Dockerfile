FROM openjdk:24-jdk-slim

COPY target/*.jar  registrationservice.jar
COPY src/main/resources/application.properties /config/application.properties
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "registrationservice.jar"]