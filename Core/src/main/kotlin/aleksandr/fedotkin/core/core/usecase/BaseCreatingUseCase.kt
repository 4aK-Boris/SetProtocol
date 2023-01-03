package aleksandr.fedotkin.core.core.usecase

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.core.domain.repositories.general.MessageWrapperRepository

abstract class BaseCreatingUseCase<T : Model, R : DTO, S : Model>(
    messageWrapperRepository: MessageWrapperRepository<T, R>
) : BaseSetUseCase<T, R>(messageWrapperRepository = messageWrapperRepository) {

    abstract fun create(model: MessageWrapperModel<S>): MessageWrapperModel<T>
}