/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform") version "1.4.0"
    kotlin("plugin.serialization") version "1.4.0"
    application
    id("com.github.johnrengelman.shadow") version "6.0.0"
    `maven-publish`
    signing
}

group = "hu.simplexion.zakadabar"
version = "2020.8.31"

val isSnapshot = version.toString().contains("SNAPSHOT")

val stackVersion by extra { "2020.8.31" }

repositories {
    mavenCentral()
    jcenter()

    if (stackVersion.contains("SNAPSHOT")) {
        mavenLocal()
    }

    fun gps(project : String) {
        maven {
            name = "gps-$project"
            url = uri("https://maven.pkg.github.com/$project")
            metadataSources {
                gradleMetadata()
            }
            credentials {
                username = properties["github.user"].toString()
                password = properties["github.key"].toString()
            }
        }
    }

    gps("spxbhuhb/zakadabar-stack")

}

tasks.withType<KotlinCompile>().all {
    kotlinOptions.jvmTarget = "1.8"
}

kotlin {

    jvm {
        withJava()
    }

    js {
        browser()
    }

    sourceSets["commonMain"].dependencies {
        api("hu.simplexion.zakadabar:zakadabar-stack:$stackVersion")
    }

    sourceSets["jvmMain"].dependencies {
        implementation(kotlin("stdlib-jdk8"))
    }
}

// -------------------------------------------------------------
// Building the shadow jar and the application distribution
// -------------------------------------------------------------

application {
    mainClassName = "template.MainKt"
}

tasks.named<ShadowJar>("shadowJar") {
    // seems like this does not work - minimize()
}

val copyAppStruct by tasks.registering(Copy::class) {
    from("$projectDir/app")
    into("$buildDir/appDist")
    include("**")
    exclude("**/.gitignore")
}

val copyAppLib by tasks.registering(Copy::class) {
    from("$buildDir/libs")
    into("$buildDir/appDist/lib")
    include("${base.archivesBaseName}-${project.version}-all.jar")
}

val copyAppStatic by tasks.registering(Copy::class) {
    from("$buildDir/distributions")
    into("$buildDir/appDist/var/static")
    include("**")
    exclude("*.tar")
    exclude("*.zip")
}

val copyAppUsr by tasks.registering(Copy::class) {
    from("$projectDir")
    into("$buildDir/appDist/usr")
    include("README.md")
    include("LICENSE.txt")
}

val appDistribution by tasks.registering(Zip::class) {
    dependsOn(copyAppStruct, copyAppLib, copyAppStatic, copyAppUsr)

    archiveFileName.set("${base.archivesBaseName}-${project.version}-app.zip")
    destinationDirectory.set(file("$buildDir/app"))

    from("$buildDir/appDist")
}