plugins {
    kotlin("jvm") version "2.0.0"
}

group = "info.sunjin"
version = "1.0-SNAPSHOT"
val lineBotVersion = "9.2.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.linecorp.bot:line-bot-messaging-api-client:${lineBotVersion}")
    implementation("com.linecorp.bot:line-bot-insight-client:${lineBotVersion}")
    implementation("com.linecorp.bot:line-bot-manage-audience-client:${lineBotVersion}")
    implementation("com.linecorp.bot:line-bot-module-attach-client:${lineBotVersion}")
    implementation("com.linecorp.bot:line-bot-module-client:${lineBotVersion}")
    implementation("com.linecorp.bot:line-bot-shop-client:${lineBotVersion}")
    implementation("com.linecorp.bot:line-channel-access-token-client:${lineBotVersion}")
    implementation("com.linecorp.bot:line-liff-client:${lineBotVersion}")

    implementation("com.linecorp.bot:line-bot-webhook:${lineBotVersion}")
    implementation("com.linecorp.bot:line-bot-parser${lineBotVersion}") // You don't need to depend on this explicitly.

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.0")

    // implementation("com.linecorp.bot:line-bot-spring-boot-webmvc:<VERSION>")
    // implementation("com.linecorp.bot:line-bot-spring-boot-client:<VERSION>") // If you want to write spring-boot API client
    // implementation("com.linecorp.bot:line-bot-spring-boot-handler:<VERSION>") // You don't need to depend on this explicitly.
    // implementation("com.linecorp.bot:line-bot-spring-boot-web:<VERSION>") // You don't need to depend on this explicitly.
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
