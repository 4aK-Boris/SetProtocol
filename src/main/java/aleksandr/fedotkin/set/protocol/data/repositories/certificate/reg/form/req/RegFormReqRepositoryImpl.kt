package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req.RegFormReqMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.PANOnlyModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqDataModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.PANOnlyRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.RegFormReqDataRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.RegFormReqRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.EXHRepository
import java.math.BigInteger
import java.security.PrivateKey
import java.security.PublicKey

class RegFormReqRepositoryImpl(
    private val panOnlyRepository: PANOnlyRepository,
    private val regFormReqDataRepository: RegFormReqDataRepository,
    private val exhRepository: EXHRepository<RegFormReqDataModel, PANOnlyModel>,
    override val mapper: RegFormReqMapper
) : RegFormReqRepository {

    override suspend fun create(
        rrpid: BigInteger,
        lidEE: BigInteger,
        lidCA: BigInteger,
        number: String,
        publicKey: PublicKey
    ): RegFormReqModel {
        val regFormReqDataModel = regFormReqDataRepository.create(
            rrpid = rrpid,
            lidEE = lidEE,
            lidCA = lidCA
        )
        val panOnlyModel = panOnlyRepository.create(number = number)
        val exhModel = exhRepository.encrypt(
            publicKey = publicKey,
            data = regFormReqDataModel,
            secondaryData = panOnlyModel
        )
        return RegFormReqModel(exh = exhModel)
    }

    override suspend fun decrypt(
        privateKey: PrivateKey,
        model: RegFormReqModel
    ): Pair<RegFormReqDataModel, PANOnlyModel> {
        return exhRepository.decrypt(privateKey = privateKey, model = model.exh)
    }
}
