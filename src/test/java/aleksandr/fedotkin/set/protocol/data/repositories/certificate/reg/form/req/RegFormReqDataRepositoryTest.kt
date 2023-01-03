package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.data.dto.Language
import aleksandr.fedotkin.set.protocol.data.dto.RequestType
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqDataModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.RegFormReqDataRepository
import java.math.BigInteger
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RegFormReqDataRepositoryTest: BaseTestRepository<RegFormReqDataModel>()  {

    override val repository by inject<RegFormReqDataRepository>()

    override lateinit var model: RegFormReqDataModel

    val create by lazy { return@lazy repository::create }

    private lateinit var lidEE: BigInteger
    private lateinit var lidCA: BigInteger

    @Before
    override fun before() = runBlocking {
        lidEE = generateNewNumber()
        lidCA = generateNewNumber()
        model = create(rrpid, lidEE, lidCA)
    }

    @Test
    fun testCreate() = runBlocking {
        assertEquals(expected = model.rrpID, actual = rrpid)
        assertEquals(expected = model.lidEE, actual = lidEE)
        assertEquals(expected = model.lidCA, actual = lidCA)
        assertEquals(expected = model.language, actual = Language.RUSSIAN)
        assertEquals(expected = model.requestType, actual = RequestType.SIGNATURE)
    }

    @Test
    fun testCheckRRPID() = runBlocking {
        val positiveVerify = repository.checkRRPID(rrpid = rrpid, regFormReqDataModel = model)
        assertTrue(actual = positiveVerify)
        val negativeVerify = repository.checkRRPID(rrpid = generateNewNumber(), regFormReqDataModel = model)
        assertFalse(actual = negativeVerify)
    }
}
