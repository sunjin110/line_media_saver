plugins {
    kotlin("jvm") version "2.0.0"
    id("org.graalvm.buildtools.native") version "0.10.3"
    id("io.ktor.plugin") version "3.0.0"
    kotlin("plugin.serialization").version("2.0.0")
}

group = "info.sunjin"
version = "1.0-SNAPSHOT"
val lineBotVersion = "9.2.0"

val osName = System.getProperty("os.name").lowercase()
val isMac = osName.contains("mac")
val isLinux = osName.contains("linux")

repositories {
    mavenCentral()
    maven(url = uri("https://packages.jetbrains.team/maven/p/ktls/maven"))
}

dependencies {

    implementation(kotlin("stdlib"))

    testImplementation(kotlin("test"))

    implementation("ch.qos.logback:logback-classic:1.4.12")
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-cio-jvm")
    implementation("io.ktor:ktor-server-content-negotiation")
    implementation("io.ktor:ktor-serialization-kotlinx-json")

    testImplementation("io.ktor:ktor-server-test-host-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test")

//    implementation("com.linecorp.bot:line-bot-messaging-api-client:${lineBotVersion}")
//    implementation("com.linecorp.bot:line-bot-insight-client:${lineBotVersion}")
//    implementation("com.linecorp.bot:line-bot-manage-audience-client:${lineBotVersion}")
//    implementation("com.linecorp.bot:line-bot-module-attach-client:${lineBotVersion}")
//    implementation("com.linecorp.bot:line-bot-module-client:${lineBotVersion}")
//    implementation("com.linecorp.bot:line-bot-shop-client:${lineBotVersion}")
//    implementation("com.linecorp.bot:line-channel-access-token-client:${lineBotVersion}")
//    implementation("com.linecorp.bot:line-liff-client:${lineBotVersion}")
//
//    implementation("com.linecorp.bot:line-bot-webhook:${lineBotVersion}")
//    implementation("com.linecorp.bot:line-bot-parser${lineBotVersion}") // You don't need to depend on this explicitly.

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.0")

    // implementation("com.linecorp.bot:line-bot-spring-boot-webmvc:<VERSION>")
    // implementation("com.linecorp.bot:line-bot-spring-boot-client:<VERSION>") // If you want to write spring-boot API client
    // implementation("com.linecorp.bot:line-bot-spring-boot-handler:<VERSION>") // You don't need to depend on this explicitly.
    // implementation("com.linecorp.bot:line-bot-spring-boot-web:<VERSION>") // You don't need to depend on this explicitly.

// https://mvnrepository.com/artifact/com.mercateo/ktor-server-lambda-core
//    implementation("com.mercateo:ktor-server-lambda-core:1.0.1")

    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.amazonaws:aws-lambda-java-core:1.2.1")
    implementation("com.amazonaws:aws-java-sdk-lambda:1.11.907")
    implementation("com.linecorp.bot:line-bot-messaging-api-client:8.0.0")
    implementation("com.linecorp.bot:line-bot-parser:8.0.0")
    implementation("com.linecorp.bot:line-bot-webhook:8.0.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")
    implementation("com.google.code.gson:gson:2.10.1")


}

//tasks.register<Jar>("lambda") {
//    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
//    archiveFileName.set("lambda.jar")
//    manifest {
//        attributes["Main-Class"] = "cmd.lambda.MainKt"
//    }
//    from(sourceSets.main.get().output)
//
//    // 依存関係をjarに含める
//    dependsOn(configurations.runtimeClasspath)
//    from({
//        configurations.runtimeClasspath.get()
//            .filter { it.name.endsWith("jar") }
//            .map { zipTree(it) }
//    })
//}
//
tasks.register<Jar>("main") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
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

val graalvmArgs: MutableList<String> = mutableListOf();
if (isLinux) {
//  linuxだけ利用できる
    graalvmArgs.add("--static")
}

graalvmNative {
    binaries {
        named("main") {
            fallback.set(false)
            verbose.set(true)

            mainClass.set("cmd.main.MainKt")
            buildArgs(graalvmArgs)

            buildArgs.add("--initialize-at-build-time=ch.qos.logback")
            buildArgs.add("--initialize-at-build-time=io.ktor,kotlin")
            buildArgs.add("--initialize-at-build-time=org.slf4j.LoggerFactory")

            buildArgs.add("--initialize-at-build-time=org.slf4j")
            buildArgs.add("--initialize-at-build-time=kotlinx")

            buildArgs.add("-H:+InstallExitHandlers")
            buildArgs.add("-H:+ReportUnsupportedElementsAtRuntime")
            buildArgs.add("-H:+ReportExceptionStackTraces")

            imageName.set("main-native")
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
