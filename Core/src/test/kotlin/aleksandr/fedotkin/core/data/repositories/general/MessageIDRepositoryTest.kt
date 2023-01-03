package aleksandr.fedotkin.core.data.repositories.general

import aleksandr.fedotkin.core.domain.models.general.MessageIDModel
import aleksandr.fedotkin.core.domain.repositories.general.MessageIDRepository
import aleksandr.fedotkin.core.core.repository.BaseTestRepository
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
