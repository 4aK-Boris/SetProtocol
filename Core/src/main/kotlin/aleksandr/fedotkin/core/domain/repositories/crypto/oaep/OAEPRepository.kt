package aleksandr.fedotkin.core.domain.repositories.crypto.oaep

import aleksandr.fedotkin.core.core.repository.BaseSetRepository
import aleksandr.fedotkin.core.data.dto.crypto.oaep.OAEP
import aleksandr.fedotkin.core.domain.models.crypto.oaep.OAEPModel
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.SecretKey

interface OAEPRepository: BaseSetRepository<OAEPModel, OAEP> {

    suspend fun create(secretKey: SecretKey): OAEPModel

    suspend fun encrypt(model: OAEPModel, publicKey: PublicKey): ByteArray

    suspend fun decrypt(data: ByteArray, privateKey: PrivateKey): OAEPModel
}
