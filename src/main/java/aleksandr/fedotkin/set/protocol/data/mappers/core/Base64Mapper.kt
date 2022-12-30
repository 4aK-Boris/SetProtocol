package aleksandr.fedotkin.set.protocol.data.mappers.core

import aleksandr.fedotkin.set.protocol.core.mapper.CoreMapper
import java.util.*

class Base64Mapper: CoreMapper<ByteArray, String> {
    override fun map(value: ByteArray): String {
        return Base64.getEncoder().encodeToString(value)
    }

    override fun reverseMap(value: String): ByteArray {
        return Base64.getDecoder().decode(value)
    }
}