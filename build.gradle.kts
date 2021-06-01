/*
 * @copyright@
 */

import java.util.*

plugins {
    kotlin("multiplatform") version "1.4.31"
    kotlin("plugin.serialization") version "1.4.31"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.4.31"
    id("com.github.johnrengelman.shadow") version "6.0.0"
    application
    id("com.palantir.docker") version "0.25.0"
}


//    ↓ ↓ ↓ ↓   READ COMMENTS  ↓ ↓ ↓ ↓


// -----------------------------------------------------------------------------
// You don't have to modify anything above this
// -----------------------------------------------------------------------------

group = "my.application.group"
version = "2021.6.1"

tasks.register<zakadabar.gradle.CustomizeTask>("zkCustomize") {

    packageName = "my.pkg.name" // the package your code resides in

    // ---- The following parameters are optional, values show defaults

    applicationTitle = "My Application" // the title of your application, this is the title of the web pace
    copyright = "Copyright © 2020-2021, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license."
    defaultLocale = "en"

    sqlDriver = "org.h2.Driver"
    sqlDatabase = project.name
    sqlUrl = "jdbc:h2:./app/var/$sqlDatabase"
    sqlUser = "local"
    sqlPassword = UUID.randomUUID().toString()

    dockerImageName = project.name

    dockerSqlDriver = "org.postgresql.Driver"
    dockerSqlDatabase = project.name
    dockerSqlUrl = "jdbc:postgresql://localhost/$sqlDatabase"
    dockerSqlUser = "postgres"
    dockerSqlPassword = sqlPassword

}

// -----------------------------------------------------------------------------
// You don't have to modify anything below this
// -----------------------------------------------------------------------------

val isSnapshot = version.toString().contains("SNAPSHOT")

val stackVersion by extra { "2021.6.1" }
val datetimeVersion = "0.2.0"

repositories {
    mavenCentral()
    jcenter()
    maven(url = "https://kotlin.bintray.com/kotlinx/") // for kotlinx.datetime, to be changed to jcenter()

    if (stackVersion.contains("SNAPSHOT")) {
        mavenLocal()
    }
}

application {
    mainClassName = "zakadabar.stack.backend.ServerKt"
}

noArg {
    annotation("kotlinx.serialization.Serializable")
}

kotlin {

    jvm {
        withJava()
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }

    js {
        browser()
    }

    sourceSets["commonMain"].dependencies {
        implementation("hu.simplexion.zakadabar:core:$stackVersion")
        implementation("hu.simplexion.zakadabar:accounts:$stackVersion")
        implementation("hu.simplexion.zakadabar:blobs:$stackVersion")
        implementation("hu.simplexion.zakadabar:i18n:$stackVersion")
    }

    sourceSets["jvmMain"].dependencies {
        implementation("com.h2database:h2:1.4.200")
    }
}

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    // seems like this does not work - minimize()
}

val syncBuildInfo by tasks.registering(Sync::class) {
    from("$projectDir/template/zkBuild")
    inputs.property("version", project.version)
    filter { line: String ->
        line.replace("@version@", "${project.version}")
            .replace("@projectName@", project.name)
            .replace("@stackVersion@", stackVersion)
    }
    into("$projectDir/src/jvmMain/resources")
}

tasks["compileKotlinJvm"].dependsOn(syncBuildInfo)

val distDir = "$buildDir/app/${project.name}-$version-server"

val copyAppStruct by tasks.registering(Copy::class) {
    from("$projectDir/template/app")
    into(distDir)
    include("**")
    exclude("**/.gitignore")
}

val copyAppLib by tasks.registering(Copy::class) {
    from("$buildDir/libs")
    into("$distDir/lib")
    include("${base.archivesBaseName}-${project.version}-all.jar")
}

val copyAppIndex by tasks.registering(Copy::class) {
    from("$buildDir/distributions")
    into("$distDir/var/static")
    include("index.html")
    filter { line: String ->
        line.replace("""src="/${project.name}.js"""", """src="/${project.name}-${project.version}.js"""")
    }
}

val copyAppStatic by tasks.registering(Copy::class) {
    from("$buildDir/distributions")
    into("$distDir/var/static")
    include("**")

    exclude("index.html")
    exclude("*.tar")
    exclude("*.zip")

    rename("${project.name}.js", "${project.name}-${project.version}.js")
}

val copyAppUsr by tasks.registering(Copy::class) {
    from("$projectDir")
    into("$distDir/usr")
    include("README.md")
    include("LICENSE.txt")
}

val zkBuild by tasks.registering(Zip::class) {
    group = "zakadabar"

    dependsOn(tasks["build"], copyAppStruct, copyAppLib, copyAppStatic, copyAppIndex, copyAppUsr)

    archiveFileName.set("${project.name}-${project.version}-app.zip")
    destinationDirectory.set(file("$buildDir/app"))

    from(distDir)
}

val zkDockerPrepare by tasks.register<zakadabar.gradle.DockerPrepareTask>("zkDockerPrepare") {
    group = "zakadabar"
}

val zkDockerCopy by tasks.registering(Copy::class) {
    from(distDir)
    into("$buildDir/docker/local/${project.name}")
    include("**")
}

docker {

    dependsOn(zkBuild.get(), zkDockerPrepare, zkDockerCopy.get())

    name = project.name
    // this throws unsupported operation exception -- tags.add(version.toString())

    pull(true)
    setDockerfile(file("Dockerfile"))

}

val zkDocker by tasks.creating(Task::class) {
    group = "zakadabar"
    dependsOn(tasks.getByName("docker"))
}