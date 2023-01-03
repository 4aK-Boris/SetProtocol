package aleksandr.fedotkin.core.data.mappers.core.json

import aleksandr.fedotkin.core.core.Test
import aleksandr.fedotkin.core.core.mapper.BaseMapperTest
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class JsonByteArrayMapperTest : BaseMapperTest<Test, ByteArray>() {

    override val mapper by inject<JsonByteArrayMapper<Test>> { parametersOf(testMapper) }

    override suspend fun generateModel(): Test {
        return generateTestDTO()
    }
}
