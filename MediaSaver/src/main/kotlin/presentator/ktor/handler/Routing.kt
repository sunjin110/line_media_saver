package presentator.ktor.handler

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import presentator.ktor.dto.SampleRequest

fun Application.configureRouting() {
    routing {
        get("/sample") {
            val req = call.receive<SampleRequest>();
            sampleHandler(req)
            call.respondText("Hello GraalVM!")
            call.application.environment.log.info("Call made to /")
        }
    }
}
