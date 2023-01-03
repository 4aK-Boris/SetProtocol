package aleksandr.fedotkin.core.data.dto.error

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.exception.ErrorCode
import kotlinx.serialization.Serializable

@Serializable
data class ErrorTBS<T: DTO>(
    val errorCode: ErrorCode,
    val errorNonce: String,
    val errorOID: String?,
    val errorThumb: String,
    val errorMsg: ErrorMsg<T>
): DTO
