FROM openjdk:8-jdk-alpine
MAINTAINER shishir.insane@gmail.com
VOLUME /tmp
ADD target/sinthoma-bff-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8093
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]