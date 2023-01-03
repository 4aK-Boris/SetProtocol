package aleksandr.fedotkin.core.data.dto.general

import aleksandr.fedotkin.core.core.DTO
import kotlinx.serialization.Serializable

@Serializable
data class MessageID(
    val lIdC: String?,
    val lIdM: String?,
    val xId: String?
): DTO
