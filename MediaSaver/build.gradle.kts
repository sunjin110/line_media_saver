plugins {
    kotlin("jvm") version "2.0.0"
    id("org.graalvm.buildtools.native") version "0.10.3"
}

group = "info.sunjin"
version = "1.0-SNAPSHOT"
val lineBotVersion = "9.2.0"

val osName = System.getProperty("os.name").lowercase()
val isMac = osName.contains("mac")
val isLinux = osName.contains("linux")

repositories {
    mavenCentral()
}

dependencies {

    implementation(kotlin("stdlib"))

    testImplementation(kotlin("test"))
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
//tasks.register<Jar>("main") {
//    archiveFileName.set("main.jar")
//    manifest {
//        attributes["Main-Class"] = "cmd.main.MainKt"
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

var graalvmArgs: MutableList<String> = mutableListOf();
if (isLinux) {
//  linuxだけ利用できる
    graalvmArgs.add("--static")
}

graalvmNative {
    binaries {
        named("main") {
            mainClass.set("cmd.main.MainKt")
            imageName.set("main-native")
            buildArgs(graalvmArgs)
        }
        named("main") {
            mainClass.set("cmd.lambda.MainKt")
            imageName.set("lambda-native")
            buildArgs(graalvmArgs)
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
