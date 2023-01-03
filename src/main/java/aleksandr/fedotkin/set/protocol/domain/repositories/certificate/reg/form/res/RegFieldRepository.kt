package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegField
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFieldModel

interface RegFieldRepository: BaseSetRepository<RegFieldModel, RegField> {

    suspend fun create(name: String, length: Int): RegFieldModel
}
