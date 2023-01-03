package aleksandr.fedotkin.core.data.mappers.error

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.core.mapper.SetMapper
import aleksandr.fedotkin.core.data.dto.error.ErrorTBS
import aleksandr.fedotkin.core.data.mappers.core.Base64Mapper
import aleksandr.fedotkin.core.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.core.domain.models.error.ErrorTBSModel
import kotlinx.serialization.KSerializer

class ErrorTBSMapper<T: Model, R: DTO>(
    private val base64Mapper: Base64Mapper,
    private val bigIntegerMapper: BigIntegerMapper,
    private val errorMsgMapper: ErrorMsgMapper<T, R>,
    private val mapper: SetMapper<T, R>
): SetMapper<ErrorTBSModel<T>, ErrorTBS<R>> {

    override val serializer: KSerializer<ErrorTBS<R>>
        get() = ErrorTBS.serializer(mapper.serializer)

    override fun map(value: ErrorTBSModel<T>): ErrorTBS<R> {
        return ErrorTBS(
            errorCode = value.errorCode,
            errorNonce = bigIntegerMapper.map(value = value.errorNonce),
            errorOID = value.errorOID,
            errorThumb = base64Mapper.map(value = value.errorThumb),
            errorMsg = errorMsgMapper.map(value = value.errorMsg)
        )
    }

    override fun reverseMap(value: ErrorTBS<R>): ErrorTBSModel<T> {
        return ErrorTBSModel(
            errorCode = value.errorCode,
            errorNonce = bigIntegerMapper.reverseMap(value = value.errorNonce),
            errorOID = value.errorOID,
            errorThumb = base64Mapper.reverseMap(value = value.errorThumb),
            errorMsg = errorMsgMapper.reverseMap(value = value.errorMsg)
        )
    }
}
