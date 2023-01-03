package aleksandr.fedotkin.core.domain.repositories.crypto.oaep

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.core.repository.BaseSetRepository
import aleksandr.fedotkin.core.data.dto.crypto.oaep.OAEP3
import aleksandr.fedotkin.core.domain.models.crypto.oaep.OAEP3Model
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.SecretKey

interface OAEP3Repository<T: Model, R: DTO>: BaseSetRepository<OAEP3Model<T>, OAEP3<R>> {

    suspend fun create(secretKey: SecretKey, data: T): OAEP3Model<T>

    suspend fun encrypt(model: OAEP3Model<T>, publicKey: PublicKey): ByteArray

    suspend fun decrypt(data: ByteArray, privateKey: PrivateKey): OAEP3Model<T>
}
