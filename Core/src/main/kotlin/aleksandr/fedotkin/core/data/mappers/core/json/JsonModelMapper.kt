package aleksandr.fedotkin.core.data.mappers.core.json

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.core.mapper.BaseMapper
import aleksandr.fedotkin.core.core.mapper.CoreMapper

class JsonModelMapper<T : Model, R : DTO>(
    private val jsonMapper: JsonMapper<R>,
    private val mapper: BaseMapper<T, R>
) : CoreMapper<T, String> {

    override fun map(value: T): String {
        return jsonMapper.map(value = mapper.map(value))
    }

    override fun reverseMap(value: String): T {
        return mapper.reverseMap(jsonMapper.reverseMap(value = value))
    }
}
