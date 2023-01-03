package aleksandr.fedotkin.core.data.repositories.error

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.core.exception.ErrorCode
import aleksandr.fedotkin.core.data.mappers.error.UnsignedErrorMapper
import aleksandr.fedotkin.core.domain.models.error.UnsignedErrorModel
import aleksandr.fedotkin.core.domain.models.general.MessageHeaderModel
import aleksandr.fedotkin.core.domain.repositories.error.ErrorTBSRepository
import aleksandr.fedotkin.core.domain.repositories.error.UnsignedErrorRepository
import java.security.PublicKey

class UnsignedErrorRepositoryImpl<T : Model, R : DTO>(
    override val mapper: UnsignedErrorMapper<T, R>,
    private val errorTBSRepository: ErrorTBSRepository<T, R>
) : UnsignedErrorRepository<T, R> {

    override suspend fun create(
        publicKey: PublicKey,
        errorCode: ErrorCode,
        messageHeader: MessageHeaderModel,
        message: T
    ): UnsignedErrorModel<T> {
        return UnsignedErrorModel(
            errorTBS = errorTBSRepository.create(
                publicKey = publicKey,
                errorCode = errorCode,
                messageHeader = messageHeader,
                message = message
            )
        )
    }
}
