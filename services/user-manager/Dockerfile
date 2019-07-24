FROM openjdk:8-jdk-alpine
MAINTAINER shishir.insane@gmail.com
VOLUME /tmp
ADD target/user-manager-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8092
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongo/sinthoma", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]