package aleksandr.fedotkin.set.protocol.domain.useceses.client

import aleksandr.fedotkin.set.protocol.core.usecase.BaseClientRequestUseCase
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.req.RegFormReq
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res.CardCInitResTBSModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.RegFormReqRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.general.MessageWrapperRepository
import java.math.BigInteger
import java.security.PrivateKey
import java.security.PublicKey
import java.security.cert.X509Certificate

class RegFormReqClientUseCase(
    override val repository: RegFormReqRepository,
    override val publicKey: PublicKey,
    override val privateKey: PrivateKey,
    messageWrapperRepository: MessageWrapperRepository<RegFormReqModel, RegFormReq>
) : BaseClientRequestUseCase<RegFormReqModel, RegFormReq>(messageWrapperRepository) {

    override lateinit var messageWrapperModel: MessageWrapperModel<RegFormReqModel>

    override var rrpid: BigInteger = generateNewNumber()

    override suspend fun sendMessage(json: String): String {
        return networkAPI.sendCardCInit(json = json)
    }

    suspend fun createAndSend(
        number: String,
        certificate: X509Certificate,
        messageWrapperModel: MessageWrapperModel<CardCInitResTBSModel>
    ): String {
        val regFormReqModel = messageWrapperModel.messageModel.run {
            repository.create(
                rrpid = rrpid,
                lidCA = lidCA,
                lidEE = lidEE,
                number = number,
                publicKey = certificate.publicKey
            )
        }
        convertToMessageWrapperModel(messageWrapperModel = messageWrapperModel, model = regFormReqModel)
        return send()
    }
}
