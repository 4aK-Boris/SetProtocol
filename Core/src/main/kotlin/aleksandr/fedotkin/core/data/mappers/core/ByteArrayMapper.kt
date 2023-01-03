package aleksandr.fedotkin.core.data.mappers.core

import aleksandr.fedotkin.core.core.mapper.CoreMapper

class ByteArrayMapper: CoreMapper<String, ByteArray> {
    override fun map(value: String): ByteArray {
        return value.encodeToByteArray()
    }

    override fun reverseMap(value: ByteArray): String {
        return value.decodeToString()
    }
}
