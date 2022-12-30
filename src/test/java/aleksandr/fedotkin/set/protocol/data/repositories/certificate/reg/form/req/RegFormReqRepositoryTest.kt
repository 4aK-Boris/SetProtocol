package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.data.dto.Language
import aleksandr.fedotkin.set.protocol.data.dto.RequestType
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.RegFormReqRepository
import java.math.BigInteger
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject
import kotlin.test.assertEquals

class RegFormReqRepositoryTest : BaseTestRepository<RegFormReqModel>() {

    override val repository by inject<RegFormReqRepository>()

    override lateinit var model: RegFormReqModel

    val create by lazy { return@lazy repository::create }

    private val number = "1248784564577895"
    private lateinit var lidEE: BigInteger
    private lateinit var lidCA: BigInteger

    @Before
    override fun before() = runBlocking {
        lidEE = generateNewNumber()
        lidCA = generateNewNumber()
        model = create(rrpid, lidEE, lidCA, number, publicKey)
    }


    @Test
    fun testCreateAndDecrypt() = runBlocking {
        val (regFromReqDataModel, panOnlyModel) = repository.decryptAndCheck(
            rrpid = rrpid,
            privateKey = privateKey,
            model = model
        )
        assertEquals(expected = panOnlyModel.pan.toString(), actual = number)
        assertEquals(expected = regFromReqDataModel.lidEE, actual = lidEE)
        assertEquals(expected = regFromReqDataModel.lidCA, actual = lidCA)
        assertEquals(expected = regFromReqDataModel.language, actual = Language.RUSSIAN)
        assertEquals(expected = regFromReqDataModel.requestType, actual = RequestType.SIGNATURE)
    }
}
