package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegTemplateMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFieldModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegTemplateModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFieldRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegTemplateRepository

class RegTemplateRepositoryImpl(
    override val mapper: RegTemplateMapper,
    private val regFieldRepository: RegFieldRepository
): RegTemplateRepository {

    override suspend fun create(): RegTemplateModel {
        return RegTemplateModel(
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
