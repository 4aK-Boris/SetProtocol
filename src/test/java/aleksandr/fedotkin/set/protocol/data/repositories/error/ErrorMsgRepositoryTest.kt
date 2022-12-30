package aleksandr.fedotkin.set.protocol.data.repositories.error

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.data.repositories.general.MessageHeaderRepositoryTest
import aleksandr.fedotkin.set.protocol.domain.models.error.ErrorMsgModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageHeaderModel
import aleksandr.fedotkin.set.protocol.domain.repositories.error.ErrorMsgRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import kotlin.test.assertEquals

class ErrorMsgRepositoryTest: BaseTestRepository<ErrorMsgModel<TestModel>>() {

    override val repository by inject<ErrorMsgRepository<TestModel, Test>> { parametersOf(testMapper) }

    override lateinit var model: ErrorMsgModel<TestModel>

    val create by lazy { return@lazy repository::create }

    private val messageHeaderRepositoryTest by inject<MessageHeaderRepositoryTest>()

    private lateinit var testModel: TestModel
    private lateinit var  messageHeader: MessageHeaderModel

    @Before
    override fun before() = runBlocking {
        testModel = generateTestModel()
        messageHeader = messageHeaderRepositoryTest.create(rrpid)
        model = create(testModel, messageHeader)
    }

    @org.junit.Test
    fun testCreate() = runBlocking {
        assertEquals(expected = model.badWrapper, actual = testModel)
        assertEquals(expected = model.messageHeader, actual = messageHeader)
    }
}
