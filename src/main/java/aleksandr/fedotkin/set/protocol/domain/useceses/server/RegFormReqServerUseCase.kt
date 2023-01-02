package aleksandr.fedotkin.set.protocol.domain.useceses.server

import aleksandr.fedotkin.set.protocol.core.usecase.BaseServerRequestUseCase
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.req.RegFormReq
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.PANOnlyModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqDataModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.RegFormReqRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.general.MessageWrapperRepository
import java.math.BigInteger
import java.security.PrivateKey
import java.security.cert.X509Certificate

class RegFormReqServerUseCase(
    override val repository: RegFormReqRepository,
    override val certificate: X509Certificate,
    override val privateKey: PrivateKey,
    messageWrapperRepository: MessageWrapperRepository<RegFormReqModel, RegFormReq>
) : BaseServerRequestUseCase<RegFormReqModel, RegFormReq, RegFormReqDataModel>(messageWrapperRepository) {

    override lateinit var rrpid: BigInteger

    override lateinit var messageWrapperModel: MessageWrapperModel<RegFormReqModel>

    override lateinit var sendMessage: suspend (String) -> Unit

    lateinit var panOnlyModel: PANOnlyModel

    override suspend fun processingMessage(json: String): MessageWrapperModel<RegFormReqDataModel> {
        messageWrapperModel = convertFromString(json = json)
        val (regFormReqDataModel, panOnlyModel) = repository.decrypt(
            privateKey = privateKey,
            model = messageWrapperModel.messageModel
        )
        this.panOnlyModel = panOnlyModel
        rrpid = regFormReqDataModel.rrpID
        checkRRPID()
        return convertToMessageWrapperModelS(model = regFormReqDataModel)
    }
}
