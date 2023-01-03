package aleksandr.fedotkin.core.data.dto.crypto

import aleksandr.fedotkin.core.core.DTO
import kotlinx.serialization.Serializable

@Serializable
data class EXH(
    val data: String,
    val secretKey: String
): DTO
