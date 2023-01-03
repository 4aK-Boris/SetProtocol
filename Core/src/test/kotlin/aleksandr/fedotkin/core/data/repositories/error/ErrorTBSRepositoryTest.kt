package aleksandr.fedotkin.core.data.repositories.error

import aleksandr.fedotkin.core.core.Test
import aleksandr.fedotkin.core.core.TestModel
import aleksandr.fedotkin.core.core.exception.ErrorCode
import aleksandr.fedotkin.core.core.repository.BaseTestRepository
import aleksandr.fedotkin.core.data.repositories.general.MessageHeaderRepositoryTest
import aleksandr.fedotkin.core.domain.models.error.ErrorTBSModel
import aleksandr.fedotkin.core.domain.models.general.MessageHeaderModel
import aleksandr.fedotkin.core.domain.repositories.error.ErrorTBSRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class ErrorTBSRepositoryTest : BaseTestRepository<ErrorTBSModel<TestModel>>() {

    override val repository by inject<ErrorTBSRepository<TestModel, Test>> { parametersOf(testMapper) }

    override lateinit var model: ErrorTBSModel<TestModel>

    val create by lazy { return@lazy repository::create }

    private val messageHeaderRepositoryTest by inject<MessageHeaderRepositoryTest>()

    private lateinit var testModel: TestModel
    private lateinit var messageHeader: MessageHeaderModel
    private val errorCode = ErrorCode.UnknownXID

    @Before
    override fun before() = runBlocking {
        testModel = generateTestModel()
        messageHeader = messageHeaderRepositoryTest.create(rrpid)
        model = create(publicKey, errorCode, messageHeader, testModel)
    }

    @org.junit.Test
    fun testCreate() = runBlocking {
        assertEquals(expected = model.errorCode, actual = errorCode)
        assertContentEquals(expected = model.errorThumb, actual = publicKey.encoded)
        assertEquals(expected = model.errorMsg.messageHeader, actual = messageHeader)
        assertEquals(expected = model.errorMsg.badWrapper, actual = testModel)
    }

    @org.junit.Test
    fun testDecodePublicKey() = runBlocking {
        val decodePublicKey = repository.decodePublicKey(errorTBSModel = model)
        assertEquals(expected = decodePublicKey, actual = publicKey)
    }
}
