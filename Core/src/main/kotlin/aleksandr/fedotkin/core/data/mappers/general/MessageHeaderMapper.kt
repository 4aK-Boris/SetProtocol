package aleksandr.fedotkin.core.data.mappers.general

import aleksandr.fedotkin.core.core.mapper.SetMapper
import aleksandr.fedotkin.core.data.dto.general.MessageHeader
import aleksandr.fedotkin.core.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.core.data.mappers.core.DateTimeMapper
import aleksandr.fedotkin.core.domain.models.general.MessageHeaderModel
import kotlinx.serialization.KSerializer

class MessageHeaderMapper(
    private val messageIDMapper: MessageIDMapper,
    private val bigIntegerMapper: BigIntegerMapper,
    private val dateTimeMapper: DateTimeMapper
) : SetMapper<MessageHeaderModel, MessageHeader> {

    override val serializer: KSerializer<MessageHeader>
        get() = MessageHeader.serializer()

    override fun map(value: MessageHeaderModel): MessageHeader {
        return MessageHeader(
            version = value.version,
            revision = value.revision,
            messageID = value.messageIDModel?.let { messageIDMapper.map(value = it) },
            rrpId = value.rrpId?.let { bigIntegerMapper.map(value = it) },
            sWIdent = value.sWIdent,
            date = dateTimeMapper.map(value = value.date)
        )
    }

    override fun reverseMap(value: MessageHeader): MessageHeaderModel {
        return MessageHeaderModel(
            version = value.version,
            revision = value.revision,
            messageIDModel = value.messageID?.let { messageIDMapper.reverseMap(value = it) },
            rrpId = value.rrpId?.let { bigIntegerMapper.reverseMap(value = it) },
            sWIdent = value.sWIdent,
            date = dateTimeMapper.reverseMap(value = value.date)
        )
    }
}
