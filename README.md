# Zakadabar Application Template

This repository contains an application template which you may use to
start a new application project based on the [Zakadabar Stack](https://github.com/spxbhuhb/zakadabar-stack).

In this repository everything you should change when you create your own
application has "template" or "Template" in the name or content. Use search & replace
to make sure there is nothing left to change.

To build your application use the `other\appDistribution` Gradle task. This
will create an all-in-one ZIP file in the `build\app` directory. 

To run the application:

 * extract the ZIP file
 * configure the database in etc/zakadabar-server.yaml (the extracted one)
 * then:
 
```shell script
cd <into-to-the-exctracted-dir>
java -jar lib/*.jar
```

You should have a server up and running, available on port 8080.

**TODO**

Upload the ZIP-file to GitHub Packages.