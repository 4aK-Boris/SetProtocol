package aleksandr.fedotkin.set.protocol.core.usecase

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.general.MessageWrapperRepository
import java.security.PublicKey
import java.security.cert.X509Certificate

abstract class BaseServerRequestUseCase<T : Model, R : DTO, S : Model>(
    messageWrapperRepository: MessageWrapperRepository<T, R>
) : BaseSetUseCase<T, R>(messageWrapperRepository = messageWrapperRepository) {

    abstract var sendMessage: suspend (String) -> Unit

    abstract val certificate: X509Certificate

    override val publicKey: PublicKey
        get() = certificate.publicKey

    abstract suspend fun processingMessage(json: String): MessageWrapperModel<S>

    override suspend fun sendError(json: String) {
        sendMessage(json)
    }
}
