package aleksandr.fedotkin.core.data.repositories.crypto

import aleksandr.fedotkin.core.core.TestModel
import aleksandr.fedotkin.core.core.repository.BaseTestRepository
import aleksandr.fedotkin.core.domain.models.crypto.EXHModel
import aleksandr.fedotkin.core.domain.repositories.crypto.EXHRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import kotlin.test.assertEquals

class EXHRepositoryTest : BaseTestRepository<EXHModel>() {

    override val repository by inject<EXHRepository<TestModel, TestModel>> { parametersOf(testMapper, testMapper) }

    override lateinit var model: EXHModel

    val create by lazy { return@lazy repository::encrypt }

    private lateinit var testModel: TestModel
    private lateinit var secondaryTestModel: TestModel

    @Before
    override fun before() = runBlocking {
        testModel = generateTestModel()
        secondaryTestModel = generateTestModel()
        model = create(publicKey, testModel, secondaryTestModel)
    }

    @Test
    fun testEncryptAndDecrypt() = runBlocking {
        val (decryptTestModel, decryptSecondaryTestModel) = repository.decrypt(
            privateKey = privateKey,
            model = model
        )
        assertEquals(expected = decryptTestModel, actual = testModel)
        assertEquals(expected = decryptSecondaryTestModel, actual = secondaryTestModel)
    }
}
