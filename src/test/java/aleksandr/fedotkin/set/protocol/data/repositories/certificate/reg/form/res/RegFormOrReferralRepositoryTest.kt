package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormOrReferralModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFormOrReferralRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class RegFormOrReferralRepositoryTest : BaseTestRepository<RegFormOrReferralModel>() {

    override val repository by inject<RegFormOrReferralRepository>()

    override lateinit var model: RegFormOrReferralModel

    val create by lazy { return@lazy repository::create }

    @Before
    override fun before() = runBlocking {
        model = create()
    }

    @Test
    fun testCreate(): Unit = runBlocking {
        assertNotNull(actual = model.referralData.regFormID)
        assertNull(actual = model.referralData.brandLogoURL)
        assertNull(actual = model.referralData.cardLogoURL)
        assertEquals(expected = model.referralData.regFieldSeq.size, actual = 4)
        assertEquals(expected = model.regFormData.policyText, actual = "")
        assertNotNull(actual = model.regFormData.regTemplate)
    }
}
