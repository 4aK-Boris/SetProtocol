package aleksandr.fedotkin.core.data.repositories.core

import aleksandr.fedotkin.core.core.Test
import aleksandr.fedotkin.core.core.TestModel
import aleksandr.fedotkin.core.core.repository.BaseTestRepository
import aleksandr.fedotkin.core.domain.repositories.core.SignatureRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.koin.core.parameter.parametersOf
import org.koin.test.inject
import kotlin.test.assertTrue

class SignatureRepositoryTest : BaseTestRepository<Triple<TestModel, Test, ByteArray>>() {

    override val repository by inject<SignatureRepository<TestModel, Test>> {
        parametersOf(testMapper)
    }

    override lateinit var model: Triple<TestModel, Test, ByteArray>

    @Before
    override fun before() = runBlocking {
        model = Triple(generateTestModel(), generateTestDTO(), generateByteArray(size = 1024))
    }

    @org.junit.Test
    fun testModel() = runBlocking {
        val signature = repository.create(data = model.first, privateKey = privateKey)
        val verify =
            repository.verify(data = model.first, publicKey = publicKey, signatureArray = signature)
        assertTrue(actual = verify)
    }

    @org.junit.Test
    fun testDTO() = runBlocking {
        val signature = repository.create(data = model.second, privateKey = privateKey)
        val verify =
            repository.verify(data = model.second, publicKey = publicKey, signatureArray = signature)
        assertTrue(actual = verify)
    }

    @org.junit.Test
    fun testByteArray() = runBlocking {
        val signature = repository.create(data = model.third, privateKey = privateKey)
        val verify =
            repository.verify(data = model.third, publicKey = publicKey, signatureArray = signature)
        assertTrue(actual = verify)
    }
}
