package aleksandr.fedotkin.core.data.mappers.error

import aleksandr.fedotkin.core.core.Test
import aleksandr.fedotkin.core.core.TestModel
import aleksandr.fedotkin.core.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.core.data.dto.error.Error
import aleksandr.fedotkin.core.domain.models.error.ErrorModel
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

class ErrorMapperTest: BaseObjectMapperTest<ErrorModel<TestModel>, Error<Test>>() {

    override val mapper by inject<ErrorMapper<TestModel, Test>> { parametersOf(testMapper) }

    override suspend fun generateModel(): ErrorModel<TestModel> {
        return ErrorModel(
            signedError = signedErrorMapperTest.generateModel(),
            unsignedError = unsignedErrorMapperTest.generateModel()
        )
    }

    private val unsignedErrorMapperTest by inject<UnsignedErrorMapperTest>()
    private val signedErrorMapperTest by inject<SignedErrorMapperTest>()
}
