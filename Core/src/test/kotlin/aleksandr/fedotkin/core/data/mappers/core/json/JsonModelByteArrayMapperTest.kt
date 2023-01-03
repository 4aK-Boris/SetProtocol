package aleksandr.fedotkin.core.data.mappers.core.json

import aleksandr.fedotkin.core.core.Test
import aleksandr.fedotkin.core.core.TestModel
import aleksandr.fedotkin.core.core.mapper.BaseMapperTest
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class JsonModelByteArrayMapperTest : BaseMapperTest<TestModel, ByteArray>() {

    override val mapper by inject<JsonModelByteArrayMapper<TestModel, Test>> {
        parametersOf(
            testMapper
        )
    }

    override suspend fun generateModel(): TestModel {
        return generateTestModel()
    }
}
