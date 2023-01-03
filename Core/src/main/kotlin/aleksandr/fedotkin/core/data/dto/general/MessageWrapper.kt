package aleksandr.fedotkin.core.data.dto.general

import aleksandr.fedotkin.core.core.DTO
import kotlinx.serialization.Serializable

@Serializable
data class MessageWrapper<T: DTO>(
    val messageHeader: MessageHeader,
    val message: T,
    val mWExtension: String?
): DTO
