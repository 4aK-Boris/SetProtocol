package aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.res

import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.req.CardCInitReqRepositoryTest
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req.CardCInitReqModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res.CardCInitResTBSModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.res.CardCInitResTBSRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CardCInitResTBSRepositoryTest : BaseTestRepository<CardCInitResTBSModel>() {

    override val repository by inject<CardCInitResTBSRepository>()

    override lateinit var model: CardCInitResTBSModel

    val create by lazy { return@lazy repository::create }

    private lateinit var cardCInitReqModel: CardCInitReqModel

    private val cardCInitReqRepositoryTest by inject<CardCInitReqRepositoryTest>()

    @Before
    override fun before() = runBlocking {
        cardCInitReqModel = cardCInitReqRepositoryTest.create(rrpid)
        model = create(cardCInitReqModel, certificate)
    }

    @Test
    fun testCreate() = runBlocking {
        assertEquals(expected = model.rrpID, actual = rrpid)
        assertContentEquals(expected = model.thumbs, cardCInitReqModel.thumbs)
        assertEquals(expected = model.lidEE, actual = cardCInitReqModel.lidEE)
        assertEquals(expected = model.challEE, actual = cardCInitReqModel.challEE)
        assertContentEquals(expected = model.caeThumb, certificate.encoded)
    }

    @Test
    fun testSignature() = runBlocking {
        val signature = repository.createSignature(model = model, privateKey = privateKey)
        val verify = repository.verifySignature(model = model, signature = signature)
        assertTrue(actual = verify)

        var errorModel = changeRRPID(model = model)
        var error = repository.verifySignature(model = errorModel, signature = signature)
        assertFalse(actual = error)

        errorModel = changeLIDEE(model = model)
        error = repository.verifySignature(model = errorModel, signature = signature)
        assertFalse(actual = error)

        errorModel = changeChallEE(model = model)
        error = repository.verifySignature(model = errorModel, signature = signature)
        assertFalse(actual = error)

        errorModel = changeThumbs(model = model)
        error = repository.verifySignature(model = errorModel, signature = signature)
        assertFalse(actual = error)
    }

    private fun changeRRPID(model: CardCInitResTBSModel): CardCInitResTBSModel {
        return model.copy(rrpID = generateNewNumber())
    }

    private fun changeLIDEE(model: CardCInitResTBSModel): CardCInitResTBSModel {
        return model.copy(lidEE = generateNewNumber())
    }

    private fun changeChallEE(model: CardCInitResTBSModel): CardCInitResTBSModel {
        return model.copy(challEE = generateNewNumber())
    }

    private fun changeThumbs(model: CardCInitResTBSModel): CardCInitResTBSModel {
        return model.copy(thumbs = listOf(generateByteArray(size = 256)))
    }
}
