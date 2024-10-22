package presentator.ktor.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.linecorp.bot.webhook.model.Event

// @see https://developers.line.biz/ja/reference/messaging-api/#request-body

@JsonInclude(JsonInclude.Include.NON_NULL)
data class LineEvents(
    // ユーザーのID
    val destination: String,

    //
    val events: List<Event>
)
