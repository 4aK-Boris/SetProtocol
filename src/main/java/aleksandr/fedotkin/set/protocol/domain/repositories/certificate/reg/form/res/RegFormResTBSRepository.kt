package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormResTBS
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqDataModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResTBSModel
import java.security.PrivateKey
import java.security.cert.X509Certificate

interface RegFormResTBSRepository : BaseSetRepository<RegFormResTBSModel, RegFormResTBS> {

    suspend fun create(model: RegFormReqDataModel, certificate: X509Certificate): RegFormResTBSModel

    suspend fun createSignature(model: RegFormResTBSModel, privateKey: PrivateKey): ByteArray

    suspend fun verifySignature(
        model: RegFormResTBSModel,
        certificate: X509Certificate,
        signature: ByteArray
    ): Boolean
}
