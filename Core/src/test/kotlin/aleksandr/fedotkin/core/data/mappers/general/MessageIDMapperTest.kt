package aleksandr.fedotkin.core. data.mappers.general

import aleksandr.fedotkin.core.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.core.data.dto.general.MessageID
import aleksandr.fedotkin.core.domain.models.general.MessageIDModel
import org.koin.test.inject

class MessageIDMapperTest : BaseObjectMapperTest<MessageIDModel, MessageID>() {

    override val mapper by inject<MessageIDMapper>()

    override suspend fun generateModel(): MessageIDModel {
        return MessageIDModel(
            lIdC = generateNewNumber(),
            lIdM = generateNewNumber(),
            xId = generateNewNumber()
        )
    }
}
