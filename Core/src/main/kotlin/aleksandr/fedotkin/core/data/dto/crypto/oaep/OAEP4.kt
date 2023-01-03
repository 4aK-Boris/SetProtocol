package aleksandr.fedotkin.core.data.dto.crypto.oaep

import aleksandr.fedotkin.core.core.DTO
import kotlinx.serialization.Serializable

@Serializable
data class OAEP4<R: DTO>(val secretKey: String, val hash: String, val data: R): DTO
