package aleksandr.fedotkin.core.data.dto.general

import aleksandr.fedotkin.core.core.DTO
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class MessageHeader(
    val version: String,
    val revision: String,
    val messageID: MessageID?,
    val rrpId: String?,
    val sWIdent: String?,
    val date: LocalDateTime
): DTO
