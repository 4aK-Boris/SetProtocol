package aleksandr.fedotkin.core.data.mappers.error

import aleksandr.fedotkin.core.core.Test
import aleksandr.fedotkin.core.core.TestModel
import aleksandr.fedotkin.core.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.core.data.dto.error.ErrorMsg
import aleksandr.fedotkin.core.data.mappers.general.MessageHeaderMapperTest
import aleksandr.fedotkin.core.domain.models.error.ErrorMsgModel
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

class ErrorMsgMapperTest : BaseObjectMapperTest<ErrorMsgModel<TestModel>, ErrorMsg<Test>>() {

    override val mapper by inject<ErrorMsgMapper<TestModel, Test>> { parametersOf(testMapper) }

    override suspend fun generateModel(): ErrorMsgModel<TestModel> {
        return ErrorMsgModel(
            messageHeader = messageHeaderMapperTest.generateModel(),
            badWrapper = generateTestModel()
        )
    }

    private val messageHeaderMapperTest by inject<MessageHeaderMapperTest>()
}
