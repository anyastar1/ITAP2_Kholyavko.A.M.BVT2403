import java.util.Properties
import org.gradle.api.tasks.Input
import org.gradle.api.provider.Property
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.io.ByteArrayOutputStream
plugins {
    id("java")
    application
    id("com.gradleup.shadow") version "9.4.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

application {
    mainClass = "org.example.Main"
}
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
repositories {
    mavenCentral()
}

dependencies {
    implementation("ch.qos.logback:logback-classic:1.5.32")
    implementation("org.slf4j:slf4j-api:2.0.17")
    implementation("org.apache.commons:commons-lang3:3.20.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}
tasks.shadowJar {
    manifest {
        attributes(Pair("Main-Class", "org.example.Main"))
    }
}
abstract class PrintInfoTask : DefaultTask() {
    @Input
    val gitCommitHash: Property<String> = project.objects.property(String::class.java)
    @TaskAction
    fun print() {
        println("======================================")
        println("Это моя первая пользовательская задача!")
        println("Проект: ${project.name}")
        println("Версия Gradle: ${project.gradle.gradleVersion}")
        println("======================================")
        }
    }
tasks.register<PrintInfoTask>("printInfo") {
    group = "Custom"
    description = "Выводит информацию о проекте"
}
tasks.register("generateBuildPassport") {
    group = "Custom"
    description = "Создает build-passport.properties"

    doLast {
        val generatedDir = layout.buildDirectory.dir("generated/resources").get().asFile
        generatedDir.mkdirs()
        val file = generatedDir.resolve("build-passport.properties")

        val props = Properties()
        if (file.exists()) {
            file.inputStream().use { props.load(it) }
        }

        val previousBuildNumber = props.getProperty("build.number")?.toIntOrNull() ?: 0
        val newBuildNumber = previousBuildNumber + 1

        val gitHash = try {
            val process = ProcessBuilder("git", "rev-parse", "--short", "HEAD")
                .redirectErrorStream(true)
                .start()
            process.inputStream.bufferedReader().readText().trim()
        } catch (e: Exception) {
            "unknown"
        }

        props["user.name"] = System.getenv("USERNAME") ?: System.getenv("USER") ?: "unknown"
        props["os.name"] = System.getProperty("os.name")
        props["java.version"] = System.getProperty("java.version")
        props["build.time"] =
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        props["greeting"] = "Привет! Это файл сборки"
        props["git.commit"] = gitHash
        props["build.number"] = newBuildNumber.toString()

        file.outputStream().use { props.store(it, "Build Passport") }

        println("build.number=$newBuildNumber git.commit=$gitHash")
    }
}

tasks.named("processResources", Copy::class) {
    dependsOn("generateBuildPassport")
    from(layout.buildDirectory.dir("generated/resources"))
}