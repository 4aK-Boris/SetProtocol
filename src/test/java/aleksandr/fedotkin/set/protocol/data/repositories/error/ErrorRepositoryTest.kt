package aleksandr.fedotkin.set.protocol.data.repositories.error

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.exception.ErrorCode
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.data.repositories.general.MessageWrapperRepositoryTest
import aleksandr.fedotkin.set.protocol.domain.models.error.ErrorModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.error.ErrorRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import kotlin.test.assertTrue

class ErrorRepositoryTest : BaseTestRepository<ErrorModel<TestModel>>() {

    override val repository by inject<ErrorRepository<TestModel, Test>> { parametersOf(testMapper) }

    override lateinit var model: ErrorModel<TestModel>

    val create by lazy { return@lazy repository::create }

    private val messageWrapperRepositoryTest by inject<MessageWrapperRepositoryTest>()

    private lateinit var testModel: TestModel
    private lateinit var messageWrapperModel: MessageWrapperModel<TestModel>
    private val errorCode = ErrorCode.UnknownXID

    @Before
    override fun before() = runBlocking {
        testModel = generateTestModel()
        messageWrapperModel = messageWrapperRepositoryTest.create(rrpid, testModel)
        model = create(errorCode, publicKey, privateKey, messageWrapperModel)
    }

    @org.junit.Test
    fun testCreateAnSignature() = runBlocking {
        val json = repository.convertToJson(errorModel = model, messageWrapperModel = messageWrapperModel)
        val verify = repository.verifyError(json = json)
        assertTrue(actual = verify)
    }
}
