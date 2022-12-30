package aleksandr.fedotkin.set.protocol.data.repositories.core

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.core.SymmetricCipherRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.koin.core.parameter.parametersOf
import org.koin.test.inject
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class SymmetricCipherRepositoryTest: BaseTestRepository<Triple<TestModel, Test, ByteArray>>() {

    override val repository by inject<SymmetricCipherRepository<TestModel, Test>> { parametersOf(testMapper) }

    override lateinit var model: Triple<TestModel, Test, ByteArray>

    @Before
    override fun before() = runBlocking {
        model = Triple(generateTestModel(), generateTestDTO(), generateByteArray(size = 1024))
    }

    @org.junit.Test
    fun testModel() = runBlocking {
        val cipherData = repository.encrypt(data = model.first, secretKey = secretKey)
        val clearModel = repository.decryptModel(data = cipherData, secretKey = secretKey)
        assertEquals(expected = clearModel, actual = model.first)
    }

    @org.junit.Test
    fun testDTO() = runBlocking {
        val cipherData = repository.encrypt(data = model.second, secretKey = secretKey)
        val clearDTO = repository.decryptDTO(data = cipherData, secretKey = secretKey)
        assertEquals(expected = clearDTO, actual = model.second)
    }

    @org.junit.Test
    fun testByteArray() = runBlocking {
        val cipherData = repository.encrypt(data = model.third, secretKey = secretKey)
        val clearArray = repository.decrypt(data = cipherData, secretKey = secretKey)
        assertContentEquals(expected = clearArray, actual = model.third)
    }
}
