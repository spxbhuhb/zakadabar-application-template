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
import java.util.*

@Suppress("MemberVisibilityCanBePrivate")

abstract class CustomizeTask : DefaultTask() {

    private val mapping = mutableMapOf<String, String?>()

    @Input
    var projectName: String? = null

    @Input
    var applicationTitle: String? = null

    @Input
    var packageName: String? = null

    @Input
    var sqlDriver: String? = null

    @Input
    var sqlUrl: String? = null

    @Input
    var sqlDatabase: String? = null

    @Input
    var sqlUser: String? = null

    @Input
    var sqlPassword: String? = null

    @Input
    var dockerImageName: String? = null

    @Input
    var dockerSqlDb: String? = null

    @Input
    var dockerSqlUser: String? = null

    @Input
    var dockerSqlPassword: String? = null

    init {
        group = "zakadabar"

        projectName = project.name
        applicationTitle = project.name.capitalize()
        packageName = null

        sqlDriver = "org.postgresql.Driver"

        sqlDatabase = project.name
        sqlUser = "postgres"
        sqlPassword = UUID.randomUUID().toString()

        dockerImageName = project.name

        dockerSqlDb = project.name
        dockerSqlUser = "postgres"
        dockerSqlPassword = sqlPassword
    }

    private var rootDir: String = this.project.rootDir.absolutePath

    private val packageDir: String
        get() = packageName !!.replace(".", "/")

    @TaskAction
    fun customizeProject() {
        if (this.project.rootProject.name == "zakadabar-application-template") {
            throw IllegalStateException("You have to change name of the project in settings.gradle.kts!")
        }

        if (packageName == null) {
            throw IllegalStateException("You have to change the base package name in the customize task in build.gradle.kts!")
        }

        if (packageName !!.endsWith(".")) {
            throw IllegalArgumentException("The package name in gradle.properties must not end with a dot!")
        }

        if (! File(rootDir, "src/commonMain/kotlin/zakadabar/template").exists()) {
            throw IllegalArgumentException("Customization must not be run more than once!")
        }

        mapping["projectName"] = projectName
        mapping["applicationTitle"] = applicationTitle
        mapping["packageName"] = packageName

        mapping["sqlDriver"] = sqlDriver

        mapping["sqlDatabase"] = sqlDatabase
        mapping["sqlUrl"] = sqlUrl ?: "jdbc:postgresql://localhost/$sqlDatabase"
        mapping["sqlUser"] = sqlUser
        mapping["sqlPassword"] = sqlPassword

        mapping["dockerImageName"] = dockerImageName

        mapping["dockerSqlDb"] = dockerSqlDb
        mapping["dockerSqlUser"] = dockerSqlUser
        mapping["dockerSqlPassword"] = dockerSqlPassword

        println("Customising: $projectName / $packageName")

        sourceSet("commonMain")
        sourceSet("jsMain")
        sourceSet("jvmMain")

        packageNames()

        index()
        strings()

        map("template/app/etc/zakadabar.stack.server.yaml")
        map("template/app/etc/zakadabar.stack.server-docker.yaml")
        map("template/docker/Dockerfile")
        map("template/docker/docker-compose.yml")

        println("Customisation: done")
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
            .replace("<title>template</title>", "<title>${applicationTitle}</title>")
            .replace("/zakadabar-application-template.js", "/${project.rootProject.name}.js")

        Files.write(path, newContent.toByteArray(), StandardOpenOption.TRUNCATE_EXISTING)

        println("    page title: $path")
        println("    JS file name: $path")
    }

    private fun strings() {
        val path = Paths.get(rootDir, "src/commonMain/kotlin/$packageDir/resources/AppStrings.kt")
        val content = Files.readString(path)

        val newContent = content
            .replace("by \"template\"", "by \"${applicationTitle}\"")

        Files.write(path, newContent.toByteArray(), StandardOpenOption.TRUNCATE_EXISTING)

        println("    application name: $path")
    }

    private fun map(relPath: String) {

        val path = Paths.get(rootDir, relPath)

        var content = Files.readString(path)

        mapping.forEach {
            val value = it.value ?: return@forEach
            content = content.replace("@${it.key}@", value)
        }

        Files.write(path, content.toByteArray(), StandardOpenOption.TRUNCATE_EXISTING)

        println("    map: $path")
    }
}
