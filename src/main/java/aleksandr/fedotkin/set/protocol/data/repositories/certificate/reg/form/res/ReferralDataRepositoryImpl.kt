package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.ReferralDataMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.ReferralDataModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFieldModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.ReferralDataRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFieldRepository

class ReferralDataRepositoryImpl(
    override val mapper: ReferralDataMapper,
    private val regFieldRepository: RegFieldRepository
) : ReferralDataRepository {

    override suspend fun create(): ReferralDataModel {
        return ReferralDataModel(
            regFormID = generateNewNumber(),
            brandLogoURL = null,
            cardLogoURL = null,
            regFieldSeq = getFieldSeq()
        )
    }

    private suspend fun getFieldSeq(): List<RegFieldModel> {
        return listOf(
            regFieldRepository.create(name = "Номер банковской карты", length = 16),
            regFieldRepository.create(name = "Год окончания срока службы банкоской карты", length = 2),
            regFieldRepository.create(name = "Месяц окончания срока службы банкоской карты", length = 2),
            regFieldRepository.create(name = "CVC кода на обратной стороне", length = 3)
        )
    }
}
