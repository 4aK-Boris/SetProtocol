package aleksandr.fedotkin.set.protocol.data.repositories.general

import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageIDModel
import aleksandr.fedotkin.set.protocol.domain.repositories.general.MessageIDRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject
import kotlin.test.assertTrue

class MessageIDRepositoryTest: BaseTestRepository<MessageIDModel>() {

    override val repository by inject<MessageIDRepository>()

    override lateinit var model: MessageIDModel

    val create by lazy { return@lazy repository::create }

    @Before
    override fun before() = runBlocking {
        model = create()
    }

    @Test
    fun testCreate() = runBlocking {
        assertTrue(actual = model.xId != null,)
        assertTrue(actual = model.lIdC != null)
        assertTrue(actual = model.lIdM != null)
    }
}
