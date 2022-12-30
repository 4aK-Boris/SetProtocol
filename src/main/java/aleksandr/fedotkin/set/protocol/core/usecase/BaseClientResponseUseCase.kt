package aleksandr.fedotkin.set.protocol.core.usecase

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.data.network.SetNetworkAPI
import aleksandr.fedotkin.set.protocol.domain.repositories.general.MessageWrapperRepository
import org.koin.core.component.inject

abstract class BaseClientResponseUseCase<T : Model, R : DTO>(messageWrapperRepository: MessageWrapperRepository<T, R>) :
    BaseSetUseCase<T, R>(messageWrapperRepository = messageWrapperRepository) {

    protected val networkAPI by inject<SetNetworkAPI>()

    override suspend fun sendError(json: String) {
        networkAPI.sendError(json = json)
    }
}