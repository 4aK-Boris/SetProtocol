package aleksandr.fedotkin.core.data.repositories.error

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.core.exception.ErrorCode
import aleksandr.fedotkin.core.data.mappers.error.ErrorTBSMapper
import aleksandr.fedotkin.core.domain.models.error.ErrorTBSModel
import aleksandr.fedotkin.core.domain.models.general.MessageHeaderModel
import aleksandr.fedotkin.core.domain.repositories.core.KeyRepository
import aleksandr.fedotkin.core.domain.repositories.error.ErrorMsgRepository
import aleksandr.fedotkin.core.domain.repositories.error.ErrorTBSRepository
import java.security.PublicKey

class ErrorTBSRepositoryImpl<T : Model, R : DTO>(
    override val mapper: ErrorTBSMapper<T, R>,
    private val errorMsgRepository: ErrorMsgRepository<T, R>,
    private val keyRepository: KeyRepository
) : ErrorTBSRepository<T, R> {

    override suspend fun create(
        publicKey: PublicKey,
        errorCode: ErrorCode,
        messageHeader: MessageHeaderModel,
        message: T
    ): ErrorTBSModel<T> {
        return ErrorTBSModel(
            errorCode = errorCode,
            errorNonce = generateNewNumber(),
            errorOID = "Ошибка!",
            errorThumb = publicKey.encoded,
            errorMsg = errorMsgRepository.create(messageHeader = messageHeader, message = message)
        )
    }

    override suspend fun decodePublicKey(errorTBSModel: ErrorTBSModel<T>): PublicKey {
        return keyRepository.decodePublicKey(array = errorTBSModel.errorThumb)
    }
}
