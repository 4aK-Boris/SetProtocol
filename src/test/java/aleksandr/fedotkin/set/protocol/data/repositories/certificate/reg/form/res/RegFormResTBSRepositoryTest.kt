package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req.RegFormReqDataRepositoryTest
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqDataModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResTBSModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFormResTBSRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RegFormResTBSRepositoryTest: BaseTestRepository<RegFormResTBSModel>() {

    override val repository by inject<RegFormResTBSRepository>()

    override lateinit var model: RegFormResTBSModel

    val create by lazy { return@lazy repository::create }

    private val regFormReqDataRepositoryTest by inject<RegFormReqDataRepositoryTest>()

    private lateinit var regFormReqDataModel: RegFormReqDataModel

    @Before
    override fun before() = runBlocking {
        regFormReqDataModel = regFormReqDataRepositoryTest.create(rrpid, generateNewNumber(), generateNewNumber())
        model = create(regFormReqDataModel, certificate)
    }

    @Test
    fun testCreate() = runBlocking {
        assertEquals(expected = model.rrpID, actual = regFormReqDataModel.rrpID)
        assertEquals(expected = model.lidEE, actual = regFormReqDataModel.lidEE)
        assertEquals(expected = model.lidCA, actual = regFormReqDataModel.lidCA)
        assertEquals(expected = model.challEE2, actual = regFormReqDataModel.challEE2)
        assertEquals(expected = model.requestType, actual = regFormReqDataModel.requestType)
        assertEquals(expected = model.thumbs, actual = regFormReqDataModel.thumbs)
        assertTrue(actual = model.brandCRLIdentifier.isEmpty())
        assertContentEquals(expected = model.caeThumb, actual = certificate.encoded)
    }

    @Test
    fun testSignature() = runBlocking {
        val signature = repository.createSignature(model = model, privateKey = privateKey)
        val verify = repository.verifySignature(model = model, certificate = certificate, signature = signature)
        assertTrue(actual = verify)
    }
}
