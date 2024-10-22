package presentator.ktor.handler

import presentator.ktor.dto.SampleRequest

fun sampleHandler(req: SampleRequest) {
    println("hello ${req.firstName}")
}
