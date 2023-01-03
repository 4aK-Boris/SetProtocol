package aleksandr.fedotkin.core.data.repositories.error

import aleksandr.fedotkin.core.core.Test
import aleksandr.fedotkin.core.core.TestModel
import aleksandr.fedotkin.core.core.exception.ErrorCode
import aleksandr.fedotkin.core.core.repository.BaseTestRepository
import aleksandr.fedotkin.core.data.repositories.general.MessageHeaderRepositoryTest
import aleksandr.fedotkin.core.domain.models.error.UnsignedErrorModel
import aleksandr.fedotkin.core.domain.models.general.MessageHeaderModel
import aleksandr.fedotkin.core.domain.repositories.error.UnsignedErrorRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class UnsignedErrorRepositoryTest: BaseTestRepository<UnsignedErrorModel<TestModel>>() {

    override val repository by inject<UnsignedErrorRepository<TestModel, Test>> { parametersOf(testMapper) }

    override lateinit var model: UnsignedErrorModel<TestModel>

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
        assertEquals(expected = model.errorTBS.errorCode, actual = errorCode)
        assertContentEquals(expected = model.errorTBS.errorThumb, actual = publicKey.encoded)
        assertEquals(expected = model.errorTBS.errorMsg.messageHeader, actual = messageHeader)
        assertEquals(expected = model.errorTBS.errorMsg.badWrapper, actual = testModel)
    }
}
