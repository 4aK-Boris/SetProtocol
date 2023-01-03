package aleksandr.fedotkin.core.data.mappers.error

import aleksandr.fedotkin.core.core.Test
import aleksandr.fedotkin.core.core.TestModel
import aleksandr.fedotkin.core.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.core.data.dto.error.UnsignedError
import aleksandr.fedotkin.core.domain.models.error.UnsignedErrorModel
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

class UnsignedErrorMapperTest: BaseObjectMapperTest<UnsignedErrorModel<TestModel>, UnsignedError<Test>>() {

    override val mapper by inject<UnsignedErrorMapper<TestModel, Test>> { parametersOf(testMapper) }

    override suspend fun generateModel(): UnsignedErrorModel<TestModel> {
        return UnsignedErrorModel(errorTBS = errorTBSMapperTest.generateModel())
    }

    private val errorTBSMapperTest by inject<ErrorTBSMapperTest>()
}
