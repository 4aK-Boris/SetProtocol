package aleksandr.fedotkin.core.data.repositories.core

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.data.mappers.core.json.JsonByteArrayMapper
import aleksandr.fedotkin.core.data.mappers.core.json.JsonModelByteArrayMapper
import aleksandr.fedotkin.core.domain.repositories.core.MessageDigestRepository
import java.security.MessageDigest

class MessageDigestRepositoryImpl<T: Model, R: DTO>(
    private val messageDigest: MessageDigest,
    private val jsonByteArrayMapper: JsonByteArrayMapper<R>,
    private val jsonModelByteArrayMapper: JsonModelByteArrayMapper<T, R>
) : MessageDigestRepository<T, R> {

    override suspend fun messageDigest(data: T): ByteArray {
        return messageDigest(data = jsonModelByteArrayMapper.map(value = data))
    }

    override suspend fun messageDigest(data: R): ByteArray {
        return messageDigest(data = jsonByteArrayMapper.map(value = data))
    }

    override suspend fun messageDigest(data: ByteArray): ByteArray {
        return messageDigest.digest(data)
    }

    override suspend fun verify(data: T, messageDigest: ByteArray): Boolean {
        return messageDigest(data = data).contentEquals(other = messageDigest)
    }

    override suspend fun verify(data: R, messageDigest: ByteArray): Boolean {
        return messageDigest(data = data).contentEquals(other = messageDigest)
    }

    override suspend fun verify(data: ByteArray, messageDigest: ByteArray): Boolean {
        return messageDigest(data = data).contentEquals(other = messageDigest)
    }
}
