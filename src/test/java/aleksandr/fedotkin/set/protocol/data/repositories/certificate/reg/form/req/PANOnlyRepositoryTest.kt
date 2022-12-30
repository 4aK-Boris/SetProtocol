package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.PANOnlyModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.PANOnlyRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class PANOnlyRepositoryTest : BaseTestRepository<PANOnlyModel>() {

    override val repository by inject<PANOnlyRepository>()

    override lateinit var model: PANOnlyModel

    val create by lazy { return@lazy repository::create }

    private val number = "8458952458954578"

    @Before
    override fun before() = runBlocking {
        model = create(number)
    }

    @Test
    fun testCreate(): Unit = runBlocking {
        val model = create(number)
        assertEquals(expected = model.pan.toString(), actual = number)
        assertFailsWith(exceptionClass = NumberFormatException::class) {
            create("dwadwadwadwadwadwa")
        }
    }
}
