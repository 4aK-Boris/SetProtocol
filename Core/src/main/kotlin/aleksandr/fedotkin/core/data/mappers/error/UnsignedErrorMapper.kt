package aleksandr.fedotkin.core.data.mappers.error

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.data.dto.error.UnsignedError
import aleksandr.fedotkin.core.domain.models.error.UnsignedErrorModel
import aleksandr.fedotkin.core.core.mapper.SetMapper
import kotlinx.serialization.KSerializer

class UnsignedErrorMapper<T: Model, R: DTO>(
    private val errorTBSMapper: ErrorTBSMapper<T, R>,
    private val mapper: SetMapper<T, R>
): SetMapper<UnsignedErrorModel<T>, UnsignedError<R>> {

    override val serializer: KSerializer<UnsignedError<R>>
        get() = UnsignedError.serializer(mapper.serializer)


    override fun map(value: UnsignedErrorModel<T>): UnsignedError<R> {
        return UnsignedError(errorTBS = errorTBSMapper.map(value = value.errorTBS))
    }
    override fun reverseMap(value: UnsignedError<R>): UnsignedErrorModel<T> {
        return UnsignedErrorModel(errorTBS = errorTBSMapper.reverseMap(value = value.errorTBS))
    }
}
