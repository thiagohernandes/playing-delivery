FROM openjdk:11
LABEL maintainer="hernandes_sp@yahoo.com.br"

RUN mkdir /opt/playing-delivery

ENV DEPLOYMENT_JAR /opt/playing-delivery

COPY target/playing-delivery.jar $DEPLOYMENT_JAR

ENTRYPOINT exec java -jar $DEPLOYMENT_JAR/playing-delivery.jar
