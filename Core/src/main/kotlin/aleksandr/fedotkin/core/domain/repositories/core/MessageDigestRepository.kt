package aleksandr.fedotkin.core.domain.repositories.core

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.core.repository.BaseRepository

interface MessageDigestRepository<T: Model, R: DTO>: BaseRepository {

    suspend fun messageDigest(data: T): ByteArray

    suspend fun messageDigest(data: R): ByteArray

    suspend fun messageDigest(data: ByteArray): ByteArray

    suspend fun verify(data: T, messageDigest: ByteArray): Boolean

    suspend fun verify(data: R, messageDigest: ByteArray): Boolean

    suspend fun verify(data: ByteArray, messageDigest: ByteArray): Boolean
}
