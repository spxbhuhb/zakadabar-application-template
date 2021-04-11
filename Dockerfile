FROM openjdk:8-jdk-alpine
COPY local local
RUN apk update && apk add -y mc
ENTRYPOINT ["java","-jar","/local/zakadabar-template/lib/zakadabar-template-2021.3.26-all.jar", "--config", "/local/zakadabar-template/etc/zakadabar-server-docker.yaml"]