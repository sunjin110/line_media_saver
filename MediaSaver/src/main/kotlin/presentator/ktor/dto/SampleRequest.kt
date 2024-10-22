package presentator.ktor.dto

import kotlinx.serialization.*

@Serializable
data class SampleRequest(
    val id: Int,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String
)
