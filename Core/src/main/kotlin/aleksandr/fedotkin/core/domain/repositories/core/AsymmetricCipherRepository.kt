package aleksandr.fedotkin.core.domain.repositories.core

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.core.repository.BaseRepository
import java.security.PrivateKey
import java.security.PublicKey

interface AsymmetricCipherRepository<T : Model, R : DTO> : BaseRepository {

    suspend fun encrypt(data: T, publicKey: PublicKey): ByteArray

    suspend fun encrypt(data: R, publicKey: PublicKey): ByteArray

    suspend fun encrypt(data: ByteArray, publicKey: PublicKey): ByteArray

    suspend fun decryptModel(data: ByteArray, privateKey: PrivateKey): T

    suspend fun decryptDTO(data: ByteArray, privateKey: PrivateKey): R

    suspend fun decrypt(data: ByteArray, privateKey: PrivateKey): ByteArray
}
