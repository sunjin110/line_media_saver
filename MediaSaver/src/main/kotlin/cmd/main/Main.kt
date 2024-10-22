package cmd.main

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import presentator.ktor.handler.configureRouting

fun main() {
    embeddedServer(CIO, port = 8081, host = "127.0.0.1") {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
        configureRouting()
    }.start(wait = true)
}
