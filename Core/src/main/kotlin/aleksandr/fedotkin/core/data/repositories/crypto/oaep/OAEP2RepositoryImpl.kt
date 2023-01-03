package aleksandr.fedotkin.core.data.repositories.crypto.oaep

import aleksandr.fedotkin.core.data.dto.crypto.oaep.OAEP2
import aleksandr.fedotkin.core.data.mappers.crypto.oaep.OAEP2Mapper
import aleksandr.fedotkin.core.domain.models.crypto.oaep.OAEP2Model
import aleksandr.fedotkin.core.domain.repositories.core.AsymmetricCipherRepository
import aleksandr.fedotkin.core.domain.repositories.crypto.oaep.OAEP2Repository
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.SecretKey

class OAEP2RepositoryImpl(
    override val mapper: OAEP2Mapper,
    private val cipherRepository: AsymmetricCipherRepository<OAEP2Model, OAEP2>
): OAEP2Repository {

    override suspend fun create(secretKey: SecretKey, hash: ByteArray): OAEP2Model {
        return OAEP2Model(secretKey = secretKey, hash = hash)
    }

    override suspend fun encrypt(model: OAEP2Model, publicKey: PublicKey): ByteArray {
        return cipherRepository.encrypt(data = model, publicKey = publicKey)
    }

    override suspend fun decrypt(data: ByteArray, privateKey: PrivateKey): OAEP2Model {
        return cipherRepository.decryptModel(data = data, privateKey = privateKey)
    }
}
