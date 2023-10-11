FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/*.jar /app/app.jar
ENTRYPOINT ["java","-jar", "app.jar"]


