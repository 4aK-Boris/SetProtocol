package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormResTBS
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFormResTBSMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqDataModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResTBSModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFormOrReferralRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFormResTBSRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.core.SignatureRepository
import java.security.PrivateKey
import java.security.cert.X509Certificate

class RegFormResTBSRepositoryImpl(
    override val mapper: RegFormResTBSMapper,
    private val signatureRepository: SignatureRepository<RegFormResTBSModel, RegFormResTBS>,
    private val regFormOrReferralRepository: RegFormOrReferralRepository
) : RegFormResTBSRepository {

    override suspend fun create(model: RegFormReqDataModel, certificate: X509Certificate): RegFormResTBSModel {
        return RegFormResTBSModel(
            rrpID = model.rrpID,
            lidEE = model.lidEE,
            lidCA = model.lidCA,
            challEE2 = model.challEE2,
            requestType = model.requestType,
            thumbs = model.thumbs,
            brandCRLIdentifier = emptyList(),
            challCA = generateNewNumber(),
            caeThumb = certificate.encoded,
            regFormOrReferral = regFormOrReferralRepository.create()
        )
    }

    override suspend fun createSignature(model: RegFormResTBSModel, privateKey: PrivateKey): ByteArray {
        return signatureRepository.create(data = model, privateKey = privateKey)
    }

    override suspend fun checkSignature(
        model: RegFormResTBSModel,
        certificate: X509Certificate,
        signature: ByteArray
    ): Boolean {
        return signatureRepository.verify(
            data = model,
            publicKey = certificate.publicKey,
            signatureArray = signature
        )
    }
}
