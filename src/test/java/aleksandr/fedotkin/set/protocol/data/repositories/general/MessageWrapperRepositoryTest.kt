package aleksandr.fedotkin.set.protocol.data.repositories.general

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.core.repository.EasyModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.general.MessageWrapperRepository
import java.math.BigInteger
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MessageWrapperRepositoryTest : BaseTestRepository<MessageWrapperModel<TestModel>>() {

    override val repository by inject<MessageWrapperRepository<TestModel, Test>> {
        parametersOf(
            testMapper
        )
    }

    override lateinit var model: MessageWrapperModel<TestModel>

    val create by lazy { return@lazy repository::create }

    private lateinit var testModel: TestModel
    private lateinit var newRRPID: BigInteger

    @Before
    override fun before() = runBlocking {
        testModel = generateTestModel()
        model = repository.create(rrpid = rrpid, message = testModel)
        newRRPID = generateNewNumber()
    }

    @org.junit.Test
    fun testCreate() = runBlocking {
        assertTrue(actual = model.mWExtension == null)
        assertEquals(expected = model.messageModel, actual = testModel)
        assertEquals(expected = model.messageHeaderModel.rrpId, actual = rrpid)
    }

    @org.junit.Test
    fun testJsonConverter() = runBlocking {
        val json = repository.messageWrapperModelToJson(messageWrapperModel = model)
        val decodeModel = repository.jsonToMessageWrapperModel(json = json)
        assertEquals(expected = decodeModel, actual = model)
    }

    @org.junit.Test
    fun testChangeRRPID() = runBlocking {
        val newModel = repository.changeRRPID(rrpid = newRRPID, messageWrapperModel = model)
        assertEquals(expected = newModel.messageHeaderModel.rrpId, actual = newRRPID)
    }

    @org.junit.Test
    fun testChangeMessageOnOther() = runBlocking {
        val newModel = repository.changeMessageModelOnOther(
            messageModel = EasyModel(),
            messageWrapperModel = model
        )
        assertEquals(expected = newModel.messageModel, actual = EasyModel())
    }

    @org.junit.Test
    fun testChangeMessageOnOtherAndRRPID() = runBlocking {
        val newModel = repository.changeMessageModelOnOther(
            messageModel = EasyModel(),
            messageWrapperModel = model,
            rrpid = newRRPID
        )
        assertEquals(expected = newModel.messageModel, actual = EasyModel())
        assertEquals(expected = newModel.messageHeaderModel.rrpId, actual = newRRPID)
    }

    @org.junit.Test
    fun testChangeMessage() = runBlocking {
        val easyModel = EasyModel()
        val secondModel = repository.changeMessageModelOnOther(
            messageModel = easyModel,
            messageWrapperModel = model
        )
        assertEquals(expected = secondModel.messageModel, actual = easyModel)
        val thirdModel = repository.changeMessageModel(
            messageModel = testModel,
            messageWrapperModel = secondModel
        )
        assertEquals(expected = thirdModel.messageModel, actual = testModel)
    }

    @org.junit.Test
    fun testChangeMessageAndRRPID() = runBlocking {
        val secondModel = repository.changeMessageModelOnOther(
            messageModel = EasyModel(),
            messageWrapperModel = model,
            rrpid = newRRPID
        )
        val thirdModel = repository.changeMessageModel(
            messageModel = testModel,
            messageWrapperModel = secondModel,
            rrpid = rrpid
        )
        assertEquals(expected = thirdModel.messageModel, actual = testModel)
        assertEquals(expected = thirdModel.messageHeaderModel.rrpId, actual = rrpid)
    }
}
