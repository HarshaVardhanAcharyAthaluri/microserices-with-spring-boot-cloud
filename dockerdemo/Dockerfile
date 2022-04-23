FROM openjdk:11
ADD target/dockerdemo.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","app.jar"]