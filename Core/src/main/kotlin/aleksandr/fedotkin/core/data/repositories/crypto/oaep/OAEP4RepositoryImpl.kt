package aleksandr.fedotkin.core.data.repositories.crypto.oaep

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.data.dto.crypto.oaep.OAEP4
import aleksandr.fedotkin.core.data.mappers.crypto.oaep.OAEP4Mapper
import aleksandr.fedotkin.core.domain.models.crypto.oaep.OAEP4Model
import aleksandr.fedotkin.core.domain.repositories.core.AsymmetricCipherRepository
import aleksandr.fedotkin.core.domain.repositories.crypto.oaep.OAEP4Repository
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.SecretKey

class OAEP4RepositoryImpl<T: Model, R: DTO>(
    override val mapper: OAEP4Mapper<T, R>,
    private val cipherRepository: AsymmetricCipherRepository<OAEP4Model<T>, OAEP4<R>>
): OAEP4Repository<T, R> {

    override suspend fun create(secretKey: SecretKey, hash: ByteArray, data: T): OAEP4Model<T> {
        return OAEP4Model(secretKey = secretKey, hash = hash, data = data)
    }

    override suspend fun encrypt(model: OAEP4Model<T>, publicKey: PublicKey): ByteArray {
        return cipherRepository.encrypt(data = model, publicKey = publicKey)
    }

    override suspend fun decrypt(data: ByteArray, privateKey: PrivateKey): OAEP4Model<T> {
        return cipherRepository.decryptModel(data = data, privateKey = privateKey)
    }
}
