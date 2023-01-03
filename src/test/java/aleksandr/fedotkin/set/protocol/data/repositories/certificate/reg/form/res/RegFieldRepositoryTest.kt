package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFieldModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFieldRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class RegFieldRepositoryTest : BaseTestRepository<RegFieldModel>() {

    override val repository by inject<RegFieldRepository>()

    override lateinit var model: RegFieldModel

    val create by lazy { return@lazy repository::create }

    private val name = "Тест репозитория"
    private val length = 52

    @Before
    override fun before() = runBlocking {
        model = create(name, length)
    }

    @Test
    fun testCreate(): Unit = runBlocking {
        assertNotNull(actual = model.fieldId)
        assertEquals(expected = model.fieldName, actual = name)
        assertEquals(expected = model.fieldLen, actual = length)
        assertNull(actual = model.fieldDesc)
        assertTrue(actual = model.fieldRequired)
        assertFalse(actual = model.fieldInvisible)
    }
}
