package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegTemplateModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegTemplateRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class RegTemplateRepositoryTest : BaseTestRepository<RegTemplateModel>() {

    override val repository by inject<RegTemplateRepository>()

    override lateinit var model: RegTemplateModel

    val create by lazy { return@lazy repository::create }

    @Before
    override fun before() = runBlocking {
        model = create()
    }

    @Test
    fun testCreate(): Unit = runBlocking {
        assertNotNull(actual = model.regFormID)
        assertNull(actual = model.brandLogoURL)
        assertNull(actual = model.cardLogoURL)
        assertEquals(expected = model.regFieldSeq.size, actual = 4)
    }
}
