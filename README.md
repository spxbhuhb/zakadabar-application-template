# Zakadabar Application Template

This repository contains an application template which you may use to start a new application project based on [Zakadabar](https://github.com/spxbhuhb/zakadabar-stack).

## Create 

1. Create a new repository from this template ([Use this template](https://github.com/spxbhuhb/zakadabar-application-template/generate) button).

## Customize

1. Check out the new repository with IDEA.
1. Right click on `build.gradle.kts` in IDEA and then click on `Import Gradle Project`
1. Edit [settings.gradle.kts](settings.gradle.kts):
    1. Change project name.
1. Edit [build.gradle.kts](build.gradle.kts)
    1. Change `group` and `version`.
    1. Change parameters of the "zakadabar:zkCustomise" task:
        1. the package you would like to use,
        1. any other parameters you want to change.
1. Refresh gradle config in IDEA.
1. `gradle zkCustomise`

At this point you have the source code of a fully functioning application.

## Build Distributable Packages

1. `gradle zkDocker`
1. In the `build/app` directory you will find:
    * a zip file that contains your application
    * the content of the zip file extracted
    * a docker compose file which contains a PostgreSQL server and your application

## Run During Development

You can use an existing database server or start a new one in docker.

Whichever your choice is, modify [zakadabar.stack.server.yaml](template/app/etc/zakadabar.stack.server.yaml) to contain the actual database access URL and credentials.

1. `gradle run`
1. `gradle jsBrowserRun`

The first step starts the backend server. It listens on 8080. The second step starts a webpack devserver. It listens on 3000.

The webpack dev server forwards anything that starts with '/api' to the backend server.

To reload the web page after code changes, add `--continuous` to the arguments of the jsBrowserRun run configuration.
