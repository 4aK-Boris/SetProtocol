package aleksandr.fedotkin.core.domain.repositories.core

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.core.repository.BaseRepository
import java.security.PrivateKey
import java.security.PublicKey

interface SignatureRepository<T : Model, R: DTO>: BaseRepository {

    suspend fun create(
        data: T,
        privateKey: PrivateKey
    ): ByteArray

    suspend fun create(
        data: R,
        privateKey: PrivateKey
    ): ByteArray

    suspend fun create(
        data: ByteArray,
        privateKey: PrivateKey
    ): ByteArray

    suspend fun verify(
        data: T,
        publicKey: PublicKey,
        signatureArray: ByteArray
    ): Boolean

    suspend fun verify(
        data: R,
        publicKey: PublicKey,
        signatureArray: ByteArray
    ): Boolean

    suspend fun verify(
        data: ByteArray,
        publicKey: PublicKey,
        signatureArray: ByteArray
    ): Boolean
}
