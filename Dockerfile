FROM openjdk:8-jdk-alpine
COPY local local
RUN apk update && apk add -y mc
ENTRYPOINT ["java","-jar","/local/zakadabar-template/lib/zakadabar-template-all.jar", "--config", "/local/zakadabar-template/etc/zakadabar-server-docker.yaml"]