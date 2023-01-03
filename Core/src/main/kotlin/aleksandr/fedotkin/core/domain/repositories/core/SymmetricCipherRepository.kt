package aleksandr.fedotkin.core.domain.repositories.core

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.core.repository.BaseRepository
import javax.crypto.SecretKey

interface SymmetricCipherRepository<T : Model, R : DTO> : BaseRepository {

    suspend fun encrypt(data: T, secretKey: SecretKey): ByteArray

    suspend fun encrypt(data: R, secretKey: SecretKey): ByteArray

    suspend fun encrypt(data: ByteArray, secretKey: SecretKey): ByteArray

    suspend fun decryptModel(data: ByteArray, secretKey: SecretKey): T

    suspend fun decryptDTO(data: ByteArray, secretKey: SecretKey): R

    suspend fun decrypt(data: ByteArray, secretKey: SecretKey): ByteArray
}
