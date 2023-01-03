package aleksandr.fedotkin.core.domain.models.general

import aleksandr.fedotkin.core.core.Model

data class MessageWrapperModel<T: Model>(
    val messageHeaderModel: MessageHeaderModel,
    val messageModel: T,
    val mWExtension: String?
): Model
