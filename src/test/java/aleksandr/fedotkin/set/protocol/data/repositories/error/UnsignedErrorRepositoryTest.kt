package aleksandr.fedotkin.set.protocol.data.repositories.error

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.exception.ErrorCode
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.data.repositories.general.MessageHeaderRepositoryTest
import aleksandr.fedotkin.set.protocol.domain.models.error.UnsignedErrorModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageHeaderModel
import aleksandr.fedotkin.set.protocol.domain.repositories.error.UnsignedErrorRepository
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
