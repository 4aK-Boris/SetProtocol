package aleksandr.fedotkin.set.protocol.domain.useceses.server

import aleksandr.fedotkin.set.protocol.core.usecase.BaseServerResponseUseCase
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormRes
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqDataModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFormResRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.general.MessageWrapperRepository
import java.math.BigInteger
import java.security.PrivateKey
import java.security.cert.X509Certificate

class RegFormResServerUseCase(
    override val certificate: X509Certificate,
    override val privateKey: PrivateKey,
    override val repository: RegFormResRepository,
    messageWrapperRepository: MessageWrapperRepository<RegFormResModel, RegFormRes>
) : BaseServerResponseUseCase<RegFormResModel, RegFormRes>(messageWrapperRepository) {

    override lateinit var rrpid: BigInteger

    override lateinit var messageWrapperModel: MessageWrapperModel<RegFormResModel>

    override lateinit var sendMessage: suspend (String) -> Unit

    suspend fun createAndSend(messageWrapperModel: MessageWrapperModel<RegFormReqDataModel>) {
        convertToMessageWrapperModel(
            messageWrapperModel = messageWrapperModel,
            model = repository.create(
                regFormReqDataModel = messageWrapperModel.messageModel,
                certificate = certificate,
                privateKey = privateKey
            )
        )
        sendMessage(json)
    }
}
