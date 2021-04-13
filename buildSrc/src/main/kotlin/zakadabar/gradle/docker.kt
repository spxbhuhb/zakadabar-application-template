/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

abstract class DockerPrepareTask : DefaultTask() {

    private val rootDir: String = this.project.rootDir.absolutePath
    private val buildDir: String = this.project.rootProject.buildDir.path
    private val version: String = project.rootProject.version.toString()

    @TaskAction
    private fun prepareDocker() {
        Files.createDirectories(Paths.get(buildDir, "docker/local"))
        dockerFile()
        dockerCompose()
    }

    private fun dockerFile() {
        val fromPath = Paths.get(rootDir, "template/docker/Dockerfile")
        val toPath = Paths.get(buildDir, "docker/Dockerfile")

        val content = Files.readString(fromPath)

        val newContent = content
            .replace("@version@", version)

        Files.write(toPath, newContent.toByteArray(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
    }

    private fun dockerCompose() {
        val fromPath = Paths.get(rootDir, "template/docker/docker-compose.yml")
        val toPath = Paths.get(buildDir, "app/docker-compose-$version.yml")

        val content = Files.readString(fromPath)

        val newContent = content
            .replace("@version@", "/$version")

        Files.write(toPath, newContent.toByteArray(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
    }

}
