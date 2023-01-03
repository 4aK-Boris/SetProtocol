package aleksandr.fedotkin.core.data.mappers.crypto.oaep

import aleksandr.fedotkin.core.core.Test
import aleksandr.fedotkin.core.core.TestModel
import aleksandr.fedotkin.core.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.core.data.dto.crypto.oaep.OAEP3
import aleksandr.fedotkin.core.domain.models.crypto.oaep.OAEP3Model
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

class OAEP3MapperTest : BaseObjectMapperTest<OAEP3Model<TestModel>, OAEP3<Test>>() {

    override val mapper by inject<OAEP3Mapper<TestModel, Test>> { parametersOf(testMapper) }

    override suspend fun generateModel(): OAEP3Model<TestModel> {
        return OAEP3Model(secretKey = generateSecretKey(), data = generateTestModel())
    }
}
