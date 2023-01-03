package aleksandr.fedotkin.set.protocol.domain.useceses.client

import aleksandr.fedotkin.set.protocol.core.usecase.BaseClientResponseUseCase
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormRes
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqDataModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResTBSModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFormResRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.general.MessageWrapperRepository
import java.math.BigInteger
import java.security.PrivateKey
import java.security.PublicKey
import java.security.cert.X509Certificate

class RegFormResClientUseCase(
    override val publicKey: PublicKey,
    override val privateKey: PrivateKey,
    override val repository: RegFormResRepository,
    messageWrapperRepository: MessageWrapperRepository<RegFormResModel, RegFormRes>
) : BaseClientResponseUseCase<RegFormResModel, RegFormRes>(messageWrapperRepository) {

    override lateinit var messageWrapperModel: MessageWrapperModel<RegFormResModel>

    override lateinit var rrpid: BigInteger

    suspend fun processing(
        json: String,
        certificate: X509Certificate,
        regFormReqDataModel: RegFormReqDataModel,
        thumbs: List<ByteArray>
    ): MessageWrapperModel<RegFormResTBSModel> {
        messageWrapperModel = convertFromString(json = json)
        rrpid = messageWrapperModel.messageModel.regFormResTBS.rrpID
        repository.verifySignature(model = messageWrapperModel.messageModel, certificate = certificate)
        repository.check(
            model = messageWrapperModel.messageModel,
            thumbs = thumbs,
            regFormReqDataModel = regFormReqDataModel
        )
        return convertToMessageWrapperModelS(messageWrapperModel.messageModel.regFormResTBS)
    }
}
