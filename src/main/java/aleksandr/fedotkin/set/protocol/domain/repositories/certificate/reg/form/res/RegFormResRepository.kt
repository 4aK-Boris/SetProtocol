package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormRes
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqDataModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResModel
import java.security.PrivateKey
import java.security.cert.X509Certificate

interface RegFormResRepository : BaseSetRepository<RegFormResModel, RegFormRes> {

    suspend fun create(
        regFormReqDataModel: RegFormReqDataModel,
        privateKey: PrivateKey,
        certificate: X509Certificate
    ): RegFormResModel

    suspend fun checkSignature(model: RegFormResModel, certificate: X509Certificate): Boolean
}
