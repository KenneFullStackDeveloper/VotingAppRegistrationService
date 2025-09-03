FROM openjdk:25-jdk-slim

COPY target/*.jar  registrationservice.jar

EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "registrationservice.jar"]