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
    var applicationName: String = this.project.rootProject.name

    @Input
    var packageName: String = "zakadabar.template"

    @Input
    var rootDir: String = this.project.rootDir.absolutePath

    private val packageDir: String
        get() = packageName.replace(".", "/")

    @Input
    var sqlJdbcUrl = ""

    @Input
    var sqlUsername = "test"

    @Input
    var sqlPassword = "Almafa.12"

    @TaskAction
    fun customizeProject() {
//        if (this.project.rootProject.name == "zakadabar-application-template") {
//            throw IllegalStateException("You have to change name of the project in settings.gradle.kts!")
//        }

        if (packageName == "zakadabar.template") {
            throw IllegalStateException("You have to change the base package name in the customize task in build.gradle.kts!")
        }

        if (packageName.endsWith(".")) {
            throw IllegalArgumentException("The package name in gradle.properties must not end with a dot!")
        }

        println("Customising: $applicationName / $packageName")

        sourceSet("commonMain")
        sourceSet("jsMain")
        sourceSet("jvmMain")

        packageNames()

        index()
        strings()
        yaml()

        println("Customisation: DONE")
    }

    private fun sourceSet(targetName: String) {
        mkdir("src/$targetName/kotlin/$packageDir")
        moveAll("src/$targetName/kotlin/zakadabar/template", "src/$targetName/kotlin/$packageDir")
        mkdir("trash/$targetName")
        trash(targetName, "src/$targetName/kotlin/zakadabar")
    }

    private fun mkdir(dirs: String) {
        val path = Paths.get(rootDir, dirs)
        Files.createDirectories(path)
        println("    create: $path")
    }

    private fun moveAll(from: String, to: String) {
        val fromPath = Paths.get(rootDir, from)
        Files.list(fromPath).forEach {
            val toPath = Paths.get(rootDir, to).resolve(it.fileName)
            Files.move(it, toPath)
            println("    move: $it  >  $toPath")
        }
    }

    private fun trash(target: String, dir: String) {
        val fromPath = Paths.get(rootDir, dir)
        val targetPath = Paths.get(rootDir, "trash/$target")
        val toPath = targetPath.resolve(fromPath.fileName)
        Files.createDirectories(targetPath)
        Files.move(fromPath, toPath)
        println("    trash: $fromPath  >  $toPath")
    }

    private fun packageNames() {
        File(rootDir, "src").walk().forEach {
            if (! it.isFile) return@forEach
            if (! it.name.endsWith(".kt")) return@forEach

            val content = Files.readString(it.toPath())
            if ("zakadabar.template" !in content) return@forEach

            val newContent = content
                .replace("package zakadabar.template", "package $packageName")
                .replace("import zakadabar.template", "import $packageName")

            Files.write(it.toPath(), newContent.toByteArray(), StandardOpenOption.TRUNCATE_EXISTING)

            println("    package name: ${it.absolutePath}")
        }
    }

    private fun index() {
        val path = Paths.get(rootDir, "src/jsMain/resources/index.html")
        val content = Files.readString(path)

        val newContent = content
            .replace("<title>template</title>", "<title>${applicationName.capitalize()}</title>")
            .replace("/zakadabar-application-template.js", "/${project.rootProject.name}.js")

        Files.write(path, newContent.toByteArray(), StandardOpenOption.TRUNCATE_EXISTING)

        println("    page title: $path")
        println("    JS file name: $path")
    }

    private fun strings() {
        val path = Paths.get(rootDir, "src/commonMain/kotlin/$packageDir/resources/AppStrings.kt")
        val content = Files.readString(path)

        val newContent = content
            .replace("by \"template\"", "by \"${applicationName.capitalize()}\"")

        Files.write(path, newContent.toByteArray(), StandardOpenOption.TRUNCATE_EXISTING)

        println("    application name: $path")
    }

    private fun yaml() {
        val path = Paths.get(rootDir, "template/app/etc/zakadabar-server.yaml")
        val content = Files.readString(path)

        val jdbc = "  jdbcUrl: ${if (sqlJdbcUrl.isBlank()) "jdbc:postgresql://localhost:5432/${applicationName.toLowerCase()}" else sqlJdbcUrl}"

        val newContent = content
            .replace("  jdbcUrl: jdbc:postgresql://localhost:5432/zakadabar", jdbc)
            .replace("  username: test", "  username: $sqlUsername")
            .replace("  password: Almafa.12", "  password: $sqlPassword")
            .replace("  - zakadabar.template.backend.Module", "  - $packageName.backend.Module")

        Files.write(path, newContent.toByteArray(), StandardOpenOption.TRUNCATE_EXISTING)

        println("    application configuration: $path")
    }
}
