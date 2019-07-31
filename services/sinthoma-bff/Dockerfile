FROM openjdk:8-jdk-alpine
VOLUME /tmp
MAINTAINER shishir.insane@gmail.com
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
EXPOSE 8093
ENTRYPOINT ["java","-cp","app:app/lib/*","com.sk.sinthoma.bff.SinthomaBffApplication"]