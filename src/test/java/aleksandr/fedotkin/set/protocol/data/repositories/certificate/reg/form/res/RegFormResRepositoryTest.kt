package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req.RegFormReqDataRepositoryTest
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqDataModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFormResRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RegFormResRepositoryTest: BaseTestRepository<RegFormResModel>() {

    override val repository by inject<RegFormResRepository>()

    override lateinit var model: RegFormResModel

    val create by lazy { return@lazy repository::create }

    private val regFormReqDataRepositoryTest by inject<RegFormReqDataRepositoryTest>()

    private lateinit var regFormReqDataModel: RegFormReqDataModel

    @Before
    override fun before() = runBlocking {
        regFormReqDataModel = regFormReqDataRepositoryTest.create(rrpid, generateNewNumber(), generateNewNumber())
        model = create(regFormReqDataModel, privateKey, certificate)
    }

    @Test
    fun testCreate() = runBlocking {
        assertEquals(expected = model.regFormResTBS.rrpID, actual = regFormReqDataModel.rrpID)
        assertEquals(expected = model.regFormResTBS.lidEE, actual = regFormReqDataModel.lidEE)
        assertEquals(expected = model.regFormResTBS.lidCA, actual = regFormReqDataModel.lidCA)
        assertEquals(expected = model.regFormResTBS.challEE2, actual = regFormReqDataModel.challEE2)
        assertEquals(expected = model.regFormResTBS.requestType, actual = regFormReqDataModel.requestType)
        assertEquals(expected = model.regFormResTBS.thumbs, actual = regFormReqDataModel.thumbs)
        assertTrue(actual = model.regFormResTBS.brandCRLIdentifier.isEmpty())
        assertContentEquals(expected = model.regFormResTBS.caeThumb, actual = certificate.encoded)
        assertEquals(expected = model.signature.size, actual = SIGNATURE_LENGTH)
    }

    @Test
    fun testSignature() = runBlocking {
        val verify = repository.verifySignature(model = model, certificate = certificate)
        assertTrue(actual = verify)
    }

    companion object {
        private const val SIGNATURE_LENGTH = 256
    }
}
