package aleksandr.fedotkin.core.core.mapper

import kotlin.test.assertContentEquals

abstract class BaseByteArrayMapperTest<R>: BaseMapperTest<ByteArray, R>() {

    override fun equals(expected: ByteArray, actual: ByteArray) {
        assertContentEquals(expected = expected, actual = actual)
    }
}
