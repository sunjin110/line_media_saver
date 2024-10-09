package presentator.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.linecorp.bot.webhook.model.Event

@JsonInclude(JsonInclude.Include.NON_NULL)
data class LineEvents(val events: List<Event>)
