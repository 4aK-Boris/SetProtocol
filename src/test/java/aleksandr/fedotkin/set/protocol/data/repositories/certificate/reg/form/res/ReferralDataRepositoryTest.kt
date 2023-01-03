package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.ReferralDataModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.ReferralDataRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ReferralDataRepositoryTest : BaseTestRepository<ReferralDataModel>() {

    override val repository by inject<ReferralDataRepository>()

    override lateinit var model: ReferralDataModel

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
