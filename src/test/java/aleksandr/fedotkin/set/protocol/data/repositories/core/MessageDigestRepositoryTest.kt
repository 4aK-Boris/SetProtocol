package aleksandr.fedotkin.set.protocol.data.repositories.core

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.core.MessageDigestRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.koin.core.parameter.parametersOf
import org.koin.test.inject
import kotlin.test.assertTrue

class MessageDigestRepositoryTest: BaseTestRepository<Triple<TestModel, Test, ByteArray>>() {

    override val repository by inject<MessageDigestRepository<TestModel, Test>> { parametersOf(testMapper) }

    override lateinit var model: Triple<TestModel, Test, ByteArray>

    @Before
    override fun before() = runBlocking {
        model = Triple(generateTestModel(), generateTestDTO(), generateByteArray(size = 1024))
    }

    @org.junit.Test
    fun testModel() = runBlocking {
        val messageDigest = repository.messageDigest(data = model.first)
        val verify = repository.verify(data = model.first, messageDigest = messageDigest)
        assertTrue(actual = verify)
    }

    @org.junit.Test
    fun testDTO() = runBlocking {
        val messageDigest = repository.messageDigest(data = model.second)
        val verify = repository.verify(data = model.second, messageDigest = messageDigest)
        assertTrue(actual = verify)
    }

    @org.junit.Test
    fun testByteArray() = runBlocking {
        val messageDigest = repository.messageDigest(data = model.third)
        val verify = repository.verify(data = model.third, messageDigest = messageDigest)
        assertTrue(actual = verify)
    }
}
