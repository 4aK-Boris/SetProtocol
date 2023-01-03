package aleksandr.fedotkin.core.data.mappers.error

import aleksandr.fedotkin.core.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.core.data.dto.error.SignedError
import aleksandr.fedotkin.core.domain.models.error.SignedErrorModel
import org.koin.test.inject

class SignedErrorMapperTest : BaseObjectMapperTest<SignedErrorModel, SignedError>() {

    override val mapper by inject<SignedErrorMapper>()

    override suspend fun generateModel(): SignedErrorModel {
        return SignedErrorModel(signature = generateByteArray(size = 32))
    }
}
