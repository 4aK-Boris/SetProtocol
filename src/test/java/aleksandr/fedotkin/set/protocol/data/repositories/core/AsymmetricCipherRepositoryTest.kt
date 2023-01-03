package aleksandr.fedotkin.set.protocol.data.repositories.core

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.core.AsymmetricCipherRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.koin.core.parameter.parametersOf
import org.koin.test.inject
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class AsymmetricCipherRepositoryTest: BaseTestRepository<TestModel>() {

    override val repository by inject<AsymmetricCipherRepository<TestModel, Test>> { parametersOf(testMapper) }

    override lateinit var model: TestModel

    @Before
    override fun before() = runBlocking {
        model = generateTestModel()
    }

    @org.junit.Test
    fun testModel() = runBlocking {
        val cipherData = repository.encrypt(data = model, publicKey = publicKey)
        val clearModel = repository.decryptModel(data = cipherData, privateKey = privateKey)
        assertEquals(expected = clearModel, actual = model)
    }

    @org.junit.Test
    fun testDTO() = runBlocking {
        val dto = generateTestDTO()
        val cipherData = repository.encrypt(data = dto, publicKey = publicKey)
        val clearDTO = repository.decryptDTO(data = cipherData, privateKey = privateKey)
        assertEquals(expected = clearDTO, actual = dto)
    }

    @org.junit.Test
    fun testByteArray() = runBlocking {
        val array = generateByteArray(size = 128)
        val cipherData = repository.encrypt(data = array, publicKey = publicKey)
        val clearArray = repository.decrypt(data = cipherData, privateKey = privateKey)
        assertContentEquals(expected = clearArray, actual = array)
    }
}
