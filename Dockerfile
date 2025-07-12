FROM openjdk:21
EXPOSE 8080
ADD target/spring-mongo-app.jar spring-mongo-app.jar
ENTRYPOINT ["java", "-jar", "spring-mongo-app.jar"]