package aleksandr.fedotkin.core.data.dto.crypto

import kotlinx.serialization.Serializable

@Serializable
data class Enc(
    val signature: String,
    val secretKey: String,
    val data: String
)
