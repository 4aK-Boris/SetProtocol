package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.res

import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.res.CardCInitRes
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req.CardCInitReqModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res.CardCInitResModel
import java.math.BigInteger
import java.security.PrivateKey
import java.security.cert.X509Certificate

interface CardCInitResRepository : BaseSetRepository<CardCInitResModel, CardCInitRes> {

    suspend fun check(
        rrpid: BigInteger?,
        cardCInitReqModel: CardCInitReqModel,
        cardCInitResModel: CardCInitResModel
    )

    suspend fun create(
        cardCInitReqModel: CardCInitReqModel,
        certificate: X509Certificate,
        privateKey: PrivateKey
    ): CardCInitResModel
}
