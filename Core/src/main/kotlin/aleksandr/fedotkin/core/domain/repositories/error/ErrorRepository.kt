package aleksandr.fedotkin.core.domain.repositories.error

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.core.exception.ErrorCode
import aleksandr.fedotkin.core.core.repository.BaseSetRepository
import aleksandr.fedotkin.core.domain.models.error.ErrorModel
import aleksandr.fedotkin.core.data.dto.error.Error
import aleksandr.fedotkin.core.domain.models.general.MessageWrapperModel
import java.security.PrivateKey
import java.security.PublicKey

interface ErrorRepository<T: Model, R: DTO>: BaseSetRepository<ErrorModel<T>, Error<R>> {

    suspend fun verifyError(
        json: String,
    ): Boolean



    suspend fun create(
        errorCode: ErrorCode,
        publicKey: PublicKey,
        privateKey: PrivateKey,
        messageWrapperModel: MessageWrapperModel<T>
    ): ErrorModel<T>

    suspend fun convertToJson(errorModel: ErrorModel<T>, messageWrapperModel: MessageWrapperModel<T>): String
}
