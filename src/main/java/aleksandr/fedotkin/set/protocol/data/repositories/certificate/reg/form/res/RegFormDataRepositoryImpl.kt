package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFormDataMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormDataModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFormDataRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegTemplateRepository

class RegFormDataRepositoryImpl(
    override val mapper: RegFormDataMapper,
    private val regTemplateRepository: RegTemplateRepository
): RegFormDataRepository {

    override suspend fun create(): RegFormDataModel {
        return RegFormDataModel(regTemplate = regTemplateRepository.create(), policyText = "")
    }
}
