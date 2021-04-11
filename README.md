# Zakadabar Application Template

This repository contains an application template which you may use to start a new application project based on [Zakadabar](https://github.com/spxbhuhb/zakadabar-stack).

## Usage

1. Create an empty database for this development.
1. Create a new repository and use "from template".
1. Check out the new repository with IDEA.
1. Edit [settings.gradle.kts](settings.gradle.kts):
    1. Change project name.
1. Edit [build.gradle.kts](build.gradle.kts)
    1. Change parameters of the "customize" task:
        1. name of the application,
        1. the package you would like to use,
        1. database connection parameters (comment out as needed).
1. `gradle customise`
1. Check the `trash` directory: if nothing important is there (shouldn't be), delete it.
1. `gradle application:run`
1. `gradle "kotlin browser":jsBrowserRun`
1. Try out.
1. `gradle build`
1. `gradle other:appDistZip`
1. Look into the `$buildDir/${project.name}-$version-server` directory to have the package you can upload to a server.