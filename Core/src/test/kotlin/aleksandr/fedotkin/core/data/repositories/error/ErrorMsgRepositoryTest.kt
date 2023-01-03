package aleksandr.fedotkin.core.data.repositories.error

import aleksandr.fedotkin.core.core.Test
import aleksandr.fedotkin.core.core.TestModel
import aleksandr.fedotkin.core.core.repository.BaseTestRepository
import aleksandr.fedotkin.core.data.repositories.general.MessageHeaderRepositoryTest
import aleksandr.fedotkin.core.domain.models.error.ErrorMsgModel
import aleksandr.fedotkin.core.domain.models.general.MessageHeaderModel
import aleksandr.fedotkin.core.domain.repositories.error.ErrorMsgRepository
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
