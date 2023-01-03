package aleksandr.fedotkin.core.data.mappers.error

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.domain.models.error.ErrorModel
import aleksandr.fedotkin.core.data.dto.error.Error
import aleksandr.fedotkin.core.core.mapper.SetMapper

import kotlinx.serialization.KSerializer

class ErrorMapper<T: Model, R: DTO>(
    private val signedErrorMapper: SignedErrorMapper,
    private val unsignedErrorMapper: UnsignedErrorMapper<T, R>,
    private val mapper: SetMapper<T, R>,
): SetMapper<ErrorModel<T>, Error<R>> {

    override val serializer: KSerializer<Error<R>>
        get() = Error.serializer(mapper.serializer)

    override fun map(value: ErrorModel<T>): Error<R> {
        return Error(
            signedError = signedErrorMapper.map(value = value.signedError),
            unsignedError = unsignedErrorMapper.map(value = value.unsignedError)
        )
    }

    override fun reverseMap(value: Error<R>): ErrorModel<T> {
        return ErrorModel(
            signedError = signedErrorMapper.reverseMap(value = value.signedError),
            unsignedError = unsignedErrorMapper.reverseMap(value = value.unsignedError)
        )
    }
}
