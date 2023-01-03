package aleksandr.fedotkin.core.data.mappers.core

import aleksandr.fedotkin.core.core.mapper.BaseMapperTest
import java.math.BigInteger
import org.koin.core.component.inject
import kotlin.test.assertEquals

class BigIntegerMapperTest : BaseMapperTest<BigInteger, String>() {

    override val mapper by inject<BigIntegerMapper>()

    override suspend fun generateModel(): BigInteger {
        return generateNewNumber()
    }

    override fun equals(expected: BigInteger, actual: BigInteger) {
        assertEquals(expected = expected, actual = actual)
    }
}
