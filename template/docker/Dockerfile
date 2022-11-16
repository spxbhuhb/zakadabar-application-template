FROM eclipse-temurin:17-jre-alpine

COPY local local

LABEL version="@version@"

WORKDIR /local/@projectName@

ENTRYPOINT [\
    "java",\
    "-Duser.timezone=UTC",\
    "-jar",\
    "/local/@projectName@/lib/@projectName@-@version@-all.jar",\
    "--settings",\
    "/local/@projectName@/etc/stack.server-docker.yaml"\
]

EXPOSE 8080
