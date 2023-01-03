package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.ReferralData
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.ReferralDataModel

interface ReferralDataRepository: BaseSetRepository<ReferralDataModel, ReferralData> {

    suspend fun create(): ReferralDataModel
}
