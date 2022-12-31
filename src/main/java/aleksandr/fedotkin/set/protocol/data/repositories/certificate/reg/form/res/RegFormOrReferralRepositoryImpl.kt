package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFormOrReferralMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormOrReferralModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.ReferralDataRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFormDataRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFormOrReferralRepository

class RegFormOrReferralRepositoryImpl(
    override val mapper: RegFormOrReferralMapper,
    private val regFormDataRepository: RegFormDataRepository,
    private val referralDataRepository: ReferralDataRepository
): RegFormOrReferralRepository {

    override suspend fun create(): RegFormOrReferralModel {
        return RegFormOrReferralModel(
            referralData = referralDataRepository.create(),
            regFormData = regFormDataRepository.create()
        )
    }
}
