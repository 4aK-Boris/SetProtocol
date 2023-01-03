package aleksandr.fedotkin.core.data.repositories.crypto.oaep

import aleksandr.fedotkin.core.data.dto.crypto.oaep.OAEP
import aleksandr.fedotkin.core.data.mappers.crypto.oaep.OAEPMapper
import aleksandr.fedotkin.core.domain.models.crypto.oaep.OAEPModel
import aleksandr.fedotkin.core.domain.repositories.core.AsymmetricCipherRepository
import aleksandr.fedotkin.core.domain.repositories.crypto.oaep.OAEPRepository
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.SecretKey

class OAEPRepositoryImpl(
    override val mapper: OAEPMapper,
    private val cipherRepository: AsymmetricCipherRepository<OAEPModel, OAEP>
): OAEPRepository {

    override suspend fun create(secretKey: SecretKey): OAEPModel {
        return OAEPModel(secretKey = secretKey)
    }

    override suspend fun encrypt(model: OAEPModel, publicKey: PublicKey): ByteArray {
        return cipherRepository.encrypt(data = model, publicKey = publicKey)
    }

    override suspend fun decrypt(data: ByteArray, privateKey: PrivateKey): OAEPModel {
        return cipherRepository.decryptModel(data = data, privateKey = privateKey)
    }
}
