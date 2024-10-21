package cmd.main

import io.ktor.server.engine.*
import io.ktor.server.cio.*
import presentator.ktor.handler.configureRouting

fun main() {
    println("============= start server")
    embeddedServer(CIO, port = 8081, host = "0.0.0.0") {
        configureRouting()
    }.start(wait = true)
}