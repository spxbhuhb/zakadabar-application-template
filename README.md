# Zakadabar Application Template

This repository contains an application template which you may use to start a new application project based on [Zakadabar](https://github.com/spxbhuhb/zakadabar-stack).

## Usage

1. Create an empty database for this development.
1. Create a new repository from this template ([Use this template](https://github.com/spxbhuhb/zakadabar-application-template/generate) button)
1. Check out the new repository with IDEA.
1. Right click on `build.gradle.kts` in IDEA and then `Import Gradle Project`
1. Edit [settings.gradle.kts](settings.gradle.kts):
    1. Change project name.
1. Edit [build.gradle.kts](build.gradle.kts)
    1. Change parameters of the "customize" task:
        1. name of the application,
        1. the package you would like to use,
        1. database connection parameters (comment out as needed).
1. Refresh gradle config in IDEA.
1. Run `other:zakadabarCustomise`
1. Check the `trash` directory: if nothing important is there (shouldn't be), delete it.
1. Run `application:run` - starts the server
1. Run `kotlin browser:jsBrowserRun` - starts a webpack dev server and opens a browser window
1. Try out.
1. Run `build:build`
1. Run `other:appDistZip`
1. Look into the `$buildDir/${project.name}-$version-server` directory to have the package you can upload to a server.