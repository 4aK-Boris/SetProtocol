package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFormResMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqDataModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFormResRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFormResTBSRepository
import java.security.PrivateKey
import java.security.cert.X509Certificate

class RegFormResRepositoryImpl(
    override val mapper: RegFormResMapper,
    private val regFormResTBSRepository: RegFormResTBSRepository
) : RegFormResRepository {

    override suspend fun create(
        regFormReqDataModel: RegFormReqDataModel,
        privateKey: PrivateKey,
        certificate: X509Certificate
    ): RegFormResModel {
        val regFormResTBSModel = regFormResTBSRepository.create(model = regFormReqDataModel, certificate = certificate)
        val signature = regFormResTBSRepository.createSignature(model = regFormResTBSModel, privateKey = privateKey)
        return RegFormResModel(signature = signature, regFormResTBS = regFormResTBSModel)
    }

    override suspend fun checkSignature(model: RegFormResModel, certificate: X509Certificate): Boolean {
        return regFormResTBSRepository.checkSignature(
            model = model.regFormResTBS,
            certificate = certificate,
            signature = model.signature
        )
    }
}
