package aleksandr.fedotkin.core.data.repositories.crypto.oaep

import aleksandr.fedotkin.core.core.Test
import aleksandr.fedotkin.core.core.TestModel
import aleksandr.fedotkin.core.core.repository.BaseTestRepository
import aleksandr.fedotkin.core.domain.models.crypto.oaep.OAEP3Model
import aleksandr.fedotkin.core.domain.repositories.crypto.oaep.OAEP3Repository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import kotlin.test.assertEquals

class OAEP3RepositoryTest : BaseTestRepository<OAEP3Model<TestModel>>() {

    override val repository by inject<OAEP3Repository<TestModel, Test>> { parametersOf(testMapper) }

    override lateinit var model: OAEP3Model<TestModel>

    val create by lazy { return@lazy repository::create }

    private lateinit var testModel: TestModel

    @Before
    override fun before() = runBlocking {
        testModel = generateTestModel()
        model = create(secretKey, testModel)
    }

    @org.junit.Test
    fun testCreate() = runBlocking {
        assertEquals(expected = model.secretKey, actual = secretKey)
        assertEquals(expected = model.data, actual = testModel)
    }

    @org.junit.Test
    fun testEncryptAndDecrypt() = runBlocking {
        val encryptModel = repository.encrypt(model = model, publicKey = publicKey)
        val decryptModel = repository.decrypt(data = encryptModel, privateKey = privateKey)
        assertEquals(expected = decryptModel.secretKey, actual = secretKey)
        assertEquals(expected = model.data, actual = testModel)
    }
}
