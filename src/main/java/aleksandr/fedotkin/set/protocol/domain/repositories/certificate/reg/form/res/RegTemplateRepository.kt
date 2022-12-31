package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegTemplate
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegTemplateModel

interface RegTemplateRepository: BaseSetRepository<RegTemplateModel, RegTemplate> {

    suspend fun create(): RegTemplateModel
}
