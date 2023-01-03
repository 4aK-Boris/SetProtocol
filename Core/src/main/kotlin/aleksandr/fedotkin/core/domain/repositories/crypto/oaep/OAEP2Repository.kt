package aleksandr.fedotkin.core.domain.repositories.crypto.oaep

import aleksandr.fedotkin.core.core.repository.BaseSetRepository
import aleksandr.fedotkin.core.data.dto.crypto.oaep.OAEP2
import aleksandr.fedotkin.core.domain.models.crypto.oaep.OAEP2Model
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.SecretKey

interface OAEP2Repository: BaseSetRepository<OAEP2Model, OAEP2> {

    suspend fun create(secretKey: SecretKey, hash: ByteArray): OAEP2Model

    suspend fun encrypt(model: OAEP2Model, publicKey: PublicKey): ByteArray

    suspend fun decrypt(data: ByteArray, privateKey: PrivateKey): OAEP2Model
}
