package aleksandr.fedotkin.core.data.dto.error

import aleksandr.fedotkin.core.core.DTO
import kotlinx.serialization.Serializable

@Serializable
class UnsignedError<T: DTO>(
    val errorTBS: ErrorTBS<T>
): DTO
