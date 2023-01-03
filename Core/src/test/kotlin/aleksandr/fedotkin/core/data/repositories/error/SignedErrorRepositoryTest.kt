package aleksandr.fedotkin.core.data.repositories.error

import aleksandr.fedotkin.core.core.Test
import aleksandr.fedotkin.core.core.TestModel
import aleksandr.fedotkin.core.core.exception.ErrorCode
import aleksandr.fedotkin.core.core.repository.BaseTestRepository
import aleksandr.fedotkin.core.data.repositories.general.MessageHeaderRepositoryTest
import aleksandr.fedotkin.core.domain.models.error.ErrorTBSModel
import aleksandr.fedotkin.core.domain.models.error.SignedErrorModel
import aleksandr.fedotkin.core.domain.models.general.MessageHeaderModel
import aleksandr.fedotkin.core.domain.repositories.error.SignedErrorRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SignedErrorRepositoryTest: BaseTestRepository<SignedErrorModel>() {

    override val repository by inject<SignedErrorRepository<TestModel, Test>> { parametersOf(testMapper) }

    override lateinit var model: SignedErrorModel

    val create by lazy { return@lazy repository::createSignature }

    private val errorTBSRepositoryTest by inject<ErrorTBSRepositoryTest>()
    private val messageHeaderRepositoryTest by inject<MessageHeaderRepositoryTest>()

    private lateinit var testModel: TestModel
    private lateinit var messageHeader: MessageHeaderModel
    private val errorCode = ErrorCode.UnknownXID
    private lateinit var errorTBSModel: ErrorTBSModel<TestModel>

    @Before
    override fun before() = runBlocking {
        testModel = generateTestModel()
        messageHeader = messageHeaderRepositoryTest.create(rrpid)
        errorTBSModel = errorTBSRepositoryTest.create(publicKey, errorCode, messageHeader, testModel)
        model = create(errorTBSModel, privateKey)
    }

    @org.junit.Test
    fun testSignature() = runBlocking {
        val verify = repository.verify(errorTBSModel = errorTBSModel, publicKey = publicKey, signature = model)
        assertTrue(actual = verify)
        val newErrorTBSModel = errorTBSModel.copy(errorCode = ErrorCode.SignatureFailure)
        val secondVerify = repository.verify(errorTBSModel = newErrorTBSModel, publicKey = publicKey, signature = model)
        assertFalse(actual = secondVerify)
    }
}