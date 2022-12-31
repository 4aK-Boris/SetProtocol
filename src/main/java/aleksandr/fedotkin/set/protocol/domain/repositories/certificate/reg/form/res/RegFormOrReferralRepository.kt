package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormOrReferral
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormOrReferralModel

interface RegFormOrReferralRepository: BaseSetRepository<RegFormOrReferralModel, RegFormOrReferral> {

    suspend fun create(): RegFormOrReferralModel
}
