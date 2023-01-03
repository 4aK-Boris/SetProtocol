package aleksandr.fedotkin.core.data.repositories.general

import aleksandr.fedotkin.core.data.mappers.general.MessageIDMapper
import aleksandr.fedotkin.core.domain.models.general.MessageIDModel
import aleksandr.fedotkin.core.domain.repositories.general.MessageIDRepository

class MessageIDRepositoryImpl(override val mapper: MessageIDMapper) : MessageIDRepository {

    override suspend fun create(): MessageIDModel {
        return MessageIDModel(
            lIdC = generateNewNumber(),
            lIdM = generateNewNumber(),
            xId = generateNewNumber()
        )
    }
}
