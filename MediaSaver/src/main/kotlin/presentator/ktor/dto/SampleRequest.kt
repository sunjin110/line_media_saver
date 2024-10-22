package presentator.ktor.dto

import kotlinx.serialization.*

@Serializable
data class SampleRequest(val id: Int, val firstName: String, val lastName: String)
