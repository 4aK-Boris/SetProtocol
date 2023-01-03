package aleksandr.fedotkin.core.core.mapper

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.domain.repositories.core.KeyRepository
import javax.crypto.SecretKey
import org.koin.test.inject

abstract class BaseObjectMapperTest<T: Model, R: DTO>: BaseMapperTest<T, R>() {

    private val keyRepository by inject<KeyRepository>()

    protected fun generateSecretKey(): SecretKey {
        return keyRepository.generateSecretKey()
    }
}
