package aleksandr.fedotkin.core.data.mappers.core

import aleksandr.fedotkin.core.core.mapper.BaseByteArrayMapperTest
import org.koin.core.component.inject

class Base64MapperTest : BaseByteArrayMapperTest<String>() {

    override val mapper by inject<Base64Mapper>()

    override suspend fun generateModel(): ByteArray {
        return generateByteArray(size = SIZE)
    }

    companion object {
        private const val SIZE = 1024
    }
}
