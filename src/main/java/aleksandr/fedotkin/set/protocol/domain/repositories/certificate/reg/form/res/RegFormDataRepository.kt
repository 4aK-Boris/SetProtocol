package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormData
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormDataModel

interface RegFormDataRepository: BaseSetRepository<RegFormDataModel, RegFormData> {

    suspend fun create(): RegFormDataModel
}
