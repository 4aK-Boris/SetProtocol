package aleksandr.fedotkin.core.core.repository

import aleksandr.fedotkin.core.core.Test
import aleksandr.fedotkin.core.core.TestMapper
import aleksandr.fedotkin.core.core.TestModel

class TestRepositoryImpl(
    override val mapper: TestMapper
) : TestRepository {

    override suspend fun createDTO(): Test {
        return mapper.map(createModel())
    }

    override suspend fun createModel(): TestModel {
        return TestModel(number = generateNewNumber(), array = generateByteArray(size = 32))
    }
}
