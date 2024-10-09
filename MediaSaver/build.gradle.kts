plugins {
    kotlin("jvm") version "2.0.0"
}

group = "info.sunjin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.register<Jar>("lambda") {
    archiveFileName.set("lambda.jar")
    manifest {
        attributes["Main-Class"] = "cmd.lambda.MainKt"
    }
    from(sourceSets.main.get().output)

    // 依存関係をjarに含める
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get()
            .filter { it.name.endsWith("jar") }
            .map { zipTree(it) }
    })
}

tasks.register<Jar>("main") {
    archiveFileName.set("main.jar")
    manifest {
        attributes["Main-Class"] = "cmd.main.MainKt"
    }
    from(sourceSets.main.get().output)

    // 依存関係をjarに含める
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get()
            .filter { it.name.endsWith("jar") }
            .map { zipTree(it) }
    })
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}
