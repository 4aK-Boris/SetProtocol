package aleksandr.fedotkin.core.data.mappers.core.json

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.core.mapper.CoreMapper
import aleksandr.fedotkin.core.core.mapper.SetMapper
import kotlinx.serialization.json.Json

class JsonMapper <R: DTO>(
    private val mapper: SetMapper<Model, R>
): CoreMapper<R, String> {

    override fun map(value: R): String {
        return Json.encodeToString(value = value, serializer = mapper.serializer)
    }

    override fun reverseMap(value: String): R {
        return Json.decodeFromString(string = value, deserializer = mapper.serializer)
    }
}
