package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFieldMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFieldModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFieldRepository

class RegFieldRepositoryImpl(
    override val mapper: RegFieldMapper
): RegFieldRepository {

    override suspend fun create(name: String, length: Int): RegFieldModel {
        return RegFieldModel(
            fieldId = generateNewNumber(),
            fieldName = name,
            fieldDesc = null,
            fieldLen = length,
            fieldRequired = true,
            fieldInvisible = false
        )
    }
}
