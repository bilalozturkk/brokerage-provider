FROM openjdk:17-jdk-alpine
ADD target/*.jar assignment.jar
ENTRYPOINT ["java", "-jar","assignment.jar"]
EXPOSE 8080