/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

abstract class CustomizeTask : DefaultTask() {

    @Input
    val projectName = "attaboy" // this.project.rootProject.name

    @Input
    val packageName = "hu.simplexion.test"

    @Input
    val rootDir = this.project.rootDir.absolutePath

    @Input
    val packageDir = packageName.replace(".", "/")

    @TaskAction
    fun customizeProject() {
        if (projectName == "zakadabar-application-template") {
            throw IllegalStateException("You have to change name of the project in settings.gradle.kts!")
        }

        if (packageName == "zakadabar.template") {
            throw IllegalStateException("You have to change the base package name in gradle.properties!")
        }

        if (packageName.endsWith(".")) {
            throw IllegalArgumentException("The package name in gradle.properties must not end with a dot!")
        }

        println("customizing: $projectName / $packageName")

        mkdir("src/commonMain/kotlin/$packageName")
        moveAll("src/commonMain/kotlin/zakadabar/template", "src/commonMain/kotlin/$packageName")
        trash("src/commonMain/kotlin/zakadabar")
        fixPackages()
    }

    private fun mkdir(dirs: String) {
        val path = Paths.get(rootDir, dirs)
        Files.createDirectories(path)
        println("mkdir: $path")
    }

    private fun moveAll(from: String, to: String) {
        val fromPath = Paths.get(rootDir, from)
        Files.list(fromPath).forEach {
            val toPath = Paths.get(rootDir, to).resolve(it.fileName)
            Files.move(it, toPath)
            println("move: $it  >  $toPath")
        }
    }

    private fun trash(dir: String) {
        val fromPath = Paths.get(rootDir, dir)
        val toPath = Paths.get(rootDir, "trash").resolve(fromPath.fileName)
        Files.move(fromPath, toPath)
        println("trash: $fromPath")
    }

    private fun fixPackages() {
        File(rootDir, "src").walk().forEach {
            if (! it.isFile) return@forEach
            if (! it.name.endsWith(".kt")) return@forEach

            val content = Files.readString(it.toPath())
            if ("package zakadabar.template" !in content) return@forEach

            val newContent = content.replace("package zakadabar.template", "package $packageName")
            Files.write(it.toPath(), newContent.toByteArray(), StandardOpenOption.TRUNCATE_EXISTING)

            println("packageName: ${it.absolutePath}")
        }
    }
}
