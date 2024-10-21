package presentator.ktor.handler

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello GraalVM!")
            call.application.environment.log.info("Call made to /")
        }
    }
}
