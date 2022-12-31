package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormDataModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFormDataRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class RegFormDataRepositoryTest : BaseTestRepository<RegFormDataModel>() {

    override val repository by inject<RegFormDataRepository>()

    override lateinit var model: RegFormDataModel

    val create by lazy { return@lazy repository::create }

    @Before
    override fun before() = runBlocking {
        model = create()
    }

    @Test
    fun testCreate(): Unit = runBlocking {
        assertNotNull(actual = model.regTemplate)
        assertEquals(expected = model.policyText, actual = "")
    }
}
