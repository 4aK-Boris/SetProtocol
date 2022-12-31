package aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.DTO

@kotlinx.serialization.Serializable
data class RegField(
    val fieldId: String?,
    val fieldName: String,
    val fieldDesc: String?,
    val fieldLen: Int?,
    val fieldRequired: Boolean,
    val fieldInvisible: Boolean
): DTO
