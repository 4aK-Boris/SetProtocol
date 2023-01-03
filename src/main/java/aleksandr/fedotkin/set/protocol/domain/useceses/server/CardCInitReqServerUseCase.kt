package aleksandr.fedotkin.set.protocol.domain.useceses.server

import aleksandr.fedotkin.set.protocol.core.usecase.BaseServerRequestUseCase
import aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.req.CardCInitReq
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req.CardCInitReqModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.req.CardCInitReqRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.general.MessageWrapperRepository
import java.math.BigInteger
import java.security.PrivateKey
import java.security.cert.X509Certificate

class CardCInitReqServerUseCase(
    override val repository: CardCInitReqRepository,
    override val certificate: X509Certificate,
    override val privateKey: PrivateKey,
    messageWrapperRepository: MessageWrapperRepository<CardCInitReqModel, CardCInitReq>
) : BaseServerRequestUseCase<CardCInitReqModel, CardCInitReq, CardCInitReqModel>(messageWrapperRepository) {

    override lateinit var rrpid: BigInteger

    override lateinit var messageWrapperModel: MessageWrapperModel<CardCInitReqModel>

    override lateinit var sendMessage: suspend (String) -> Unit

    override suspend fun processingMessage(json: String): MessageWrapperModel<CardCInitReqModel> {
        messageWrapperModel = convertFromString(json = json)
        rrpid = messageWrapperModel.messageModel.rrpID
        checkRRPID()
        return messageWrapperModel
    }
}
