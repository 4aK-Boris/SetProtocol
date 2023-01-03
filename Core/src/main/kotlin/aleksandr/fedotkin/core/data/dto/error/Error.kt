package aleksandr.fedotkin.core.data.dto.error

import aleksandr.fedotkin.core.core.DTO
import kotlinx.serialization.Serializable

@Serializable
data class Error<T: DTO>(
    val signedError: SignedError,
    val unsignedError: UnsignedError<T>
): DTO
