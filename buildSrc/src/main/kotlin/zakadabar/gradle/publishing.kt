/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.gradle

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.publish.PublicationContainer
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.TaskContainer
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import org.gradle.plugins.signing.SigningExtension

val Project.isPublishing
    get() = project.properties["zk.publish"] != null || System.getenv("ZK_PUBLISH") != null

fun manifestAndDokka(tasks: TaskContainer): Task {

    tasks.withType<Jar> {
        manifest {
            attributes += sortedMapOf(
                "Built-By" to System.getProperty("user.name"),
                "Build-Jdk" to System.getProperty("java.version"),
                "Implementation-Vendor" to "@organizationName@",
                "Implementation-Version" to archiveVersion.get(),
                "Created-By" to org.gradle.util.GradleVersion.current()
            )
        }
    }

    val dokkaHtml = tasks["dokkaHtml"]

    val javadocJar = tasks.register("javadocJar", Jar::class) {
        dependsOn(dokkaHtml)
        archiveBaseName.set("core")
        archiveClassifier.set("javadoc")
        from(dokkaHtml.property("outputDirectory"))
    }

    tasks.getByName("build") {
        dependsOn(javadocJar)
    }

    return javadocJar.get()
}

fun SigningExtension.config(publications: PublicationContainer) {
    useGpgCmd()
    sign(publications)
}

fun PublishingExtension.config(project: Project) {

    repositories {
        maven {
            val snapshotUrl =
                project.properties["zk.publish.snapshot.url"]
                    ?: System.getenv("ZK_PUBLISH_SNAPSHOT_URL")
                    ?: "https://s01.oss.sonatype.org/content/repositories/snapshots/"

            val releaseUrl =
                project.properties["zk.publish.release.url"]
                    ?: System.getenv("ZK_PUBLISH_RELEASE_URL")
                    ?: "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"

            if ("SNAPSHOT" in project.version.toString()) {
                name = if ("s01.oss.sonatype.org" in snapshotUrl.toString()) "MavenCentral" else "Internal"
                url = project.uri(snapshotUrl)
            } else {
                name = if ("s01.oss.sonatype.org" in snapshotUrl.toString()) "MavenCentral" else "Internal"
                url = project.uri(releaseUrl)
            }

            credentials {
                username = (project.properties["zk.publish.username"] ?: System.getenv("ZK_PUBLISH_USERNAME")).toString()
                password = (project.properties["zk.publish.password"] ?: System.getenv("ZK_PUBLISH_PASSWORD")).toString()
            }
        }
    }

}

fun MavenPublication.config(source: Any, pomName: String) {

    val path = "@projectPath@"

    artifact(source)

    pom {
        description.set("@applicationTitle@")
        name.set(pomName)
        url.set("https://github.com/$path")
        scm {
            url.set("https://github.com/$path")
            connection.set("scm:git:git://github.com/$path.git")
            developerConnection.set("scm:git:ssh://git@github.com/$path.git")
        }
        licenses {
            license {
                name.set("@license@")
                url.set("@licenseUrl@")
                distribution.set("repo")
            }
        }
//        developers {
//            developer {
//                id.set("toth-istvan-zoltan")
//                name.set("Tóth István Zoltán")
//                url.set("https://github.com/toth-istvan-zoltan")
//                organization.set("@organization@")
//                organizationUrl.set("@organizationUrl")
//            }
//        }
    }

}