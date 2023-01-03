package aleksandr.fedotkin.core.data.mappers.core.json

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.mapper.CoreMapper
import aleksandr.fedotkin.core.data.mappers.core.ByteArrayMapper

class JsonByteArrayMapper<R: DTO>(private val jsonMapper: JsonMapper<R>, private val byteArrayMapper: ByteArrayMapper):
    CoreMapper<R, ByteArray> {

    override fun map(value: R): ByteArray {
        return byteArrayMapper.map(jsonMapper.map(value = value))
    }

    override fun reverseMap(value: ByteArray): R {
        return jsonMapper.reverseMap(value = byteArrayMapper.reverseMap(value = value))
    }
}
