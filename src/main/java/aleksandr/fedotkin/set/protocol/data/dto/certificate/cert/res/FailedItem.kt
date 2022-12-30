package aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.res

import aleksandr.fedotkin.set.protocol.core.DTO

@kotlinx.serialization.Serializable
data class FailedItem(
    val itemNumber: Int,
    val itemReason: String
): DTO
