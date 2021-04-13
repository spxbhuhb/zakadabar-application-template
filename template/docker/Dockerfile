FROM openjdk:8-jdk-alpine
COPY local local
RUN apk update && apk add -y mc
ENTRYPOINT ["java","-jar","/local/@projectName@/lib/@projectName@-@version@-all.jar", "--config", "/local/@projectName@/etc/zakadabar-server-docker.yaml"]
LABEL version="@version@"