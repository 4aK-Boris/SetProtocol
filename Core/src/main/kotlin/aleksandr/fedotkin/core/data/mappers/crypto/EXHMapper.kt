package aleksandr.fedotkin.core.data.mappers.crypto

import aleksandr.fedotkin.core.core.mapper.SetMapper
import aleksandr.fedotkin.core.data.dto.crypto.EXH
import aleksandr.fedotkin.core.data.mappers.core.Base64Mapper
import aleksandr.fedotkin.core.domain.models.crypto.EXHModel
import kotlinx.serialization.KSerializer

class EXHMapper(
    private val base64Mapper: Base64Mapper
): SetMapper<EXHModel, EXH> {

    override val serializer: KSerializer<EXH>
        get() = EXH.serializer()

    override fun map(value: EXHModel): EXH {
        return EXH(
            data = base64Mapper.map(value = value.data),
            secretKey = base64Mapper.map(value = value.secretKey)
        )
    }

    override fun reverseMap(value: EXH): EXHModel {
        return EXHModel(
            data = base64Mapper.reverseMap(value = value.data),
            secretKey = base64Mapper.reverseMap(value = value.secretKey)
        )
    }
}
