package aleksandr.fedotkin.core.data.mappers.crypto.oaep

import aleksandr.fedotkin.core.core.Test
import aleksandr.fedotkin.core.core.TestModel
import aleksandr.fedotkin.core.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.core.data.dto.crypto.oaep.OAEP4
import aleksandr.fedotkin.core.domain.models.crypto.oaep.OAEP4Model
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

class OAEP4MapperTest : BaseObjectMapperTest<OAEP4Model<TestModel>, OAEP4<Test>>() {

    override val mapper by inject<OAEP4Mapper<TestModel, Test>> { parametersOf(testMapper) }

    override suspend fun generateModel(): OAEP4Model<TestModel> {
        return OAEP4Model(
            secretKey = generateSecretKey(),
            hash = generateByteArray(size = 32),
            data = generateTestModel()
        )
    }
}
