package aleksandr.fedotkin.core.data.dto.crypto

import aleksandr.fedotkin.core.core.DTO
import kotlinx.serialization.Serializable

@Serializable
data class EncK(
    val signature: String,
    val data: String,
): DTO
