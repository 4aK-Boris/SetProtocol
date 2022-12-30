package aleksandr.fedotkin.set.protocol.data.repositories.general

import aleksandr.fedotkin.set.protocol.core.Settings
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageHeaderModel
import aleksandr.fedotkin.set.protocol.domain.repositories.general.MessageHeaderRepository
import java.time.LocalDateTime
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MessageHeaderRepositoryTest: BaseTestRepository<MessageHeaderModel>() {

    override val repository by inject<MessageHeaderRepository>()

    override lateinit var model: MessageHeaderModel

    val create by lazy { return@lazy repository::create }

    private lateinit var dateTime: LocalDateTime

    @Before
    override fun before() = runBlocking {
        model = create(rrpid)
        dateTime = LocalDateTime.now()
    }

    @Test
    fun testCreate() = runBlocking {
        assertEquals(expected = model.rrpId,  actual = rrpid)
        assertEquals(expected = model.date.dayOfMonth, actual = dateTime.dayOfMonth)
        assertEquals(expected = model.date.dayOfWeek, actual = dateTime.dayOfWeek)
        assertEquals(expected = model.date.dayOfYear, actual = dateTime.dayOfYear)
        assertEquals(expected = model.date.dayOfMonth, actual = dateTime.dayOfMonth)
        assertEquals(expected = model.date.hour, actual = dateTime.hour)
        assertEquals(expected = model.date.minute, actual = dateTime.minute)
        assertEquals(expected = model.date.year, actual = dateTime.year)
        assertEquals(expected = model.revision, actual = Settings.REVISION)
        assertEquals(expected = model.version, actual = Settings.VERSION)
        assertEquals(expected = model.sWIdent, actual = Settings.SWIDENT)
        assertTrue(actual = model.messageIDModel != null)
    }
}
