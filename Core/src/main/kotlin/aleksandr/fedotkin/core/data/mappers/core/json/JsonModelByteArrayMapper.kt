package aleksandr.fedotkin.core.data.mappers.core.json

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.core.mapper.BaseMapper
import aleksandr.fedotkin.core.core.mapper.CoreMapper
import aleksandr.fedotkin.core.data.mappers.core.ByteArrayMapper

class JsonModelByteArrayMapper<T : Model, R : DTO>(
    private val byteArrayMapper: ByteArrayMapper,
    private val jsonMapper: JsonMapper<R>,
    private val mapper: BaseMapper<T, R>
) : CoreMapper<T, ByteArray> {

    override fun map(value: T): ByteArray {
        return byteArrayMapper.map(value = jsonMapper.map(value = mapper.map(value)))
    }

    override fun reverseMap(value: ByteArray): T {
        return mapper.reverseMap(jsonMapper.reverseMap(value = byteArrayMapper.reverseMap(value)))
    }
}
