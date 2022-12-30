package aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.res

import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.req.CardCInitReqRepositoryTest
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req.CardCInitReqModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res.CardCInitResModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.res.CardCInitResRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class CardCInitResRepositoryTest: BaseTestRepository<CardCInitResModel>() {

    override val repository by inject<CardCInitResRepository>()

    override lateinit var model: CardCInitResModel

    val create by lazy { return@lazy repository::create }

    private lateinit var cardCInitReqModel: CardCInitReqModel

    private val cardCInitReqRepositoryTest by inject<CardCInitReqRepositoryTest>()

    @Before
    override fun before() = runBlocking {
        cardCInitReqModel = cardCInitReqRepositoryTest.create(rrpid)
        model = create(cardCInitReqModel, certificate, privateKey)
    }

    @Test
    fun testCreate() = runBlocking {
        assertEquals(expected = model.cardCInitResTBS.rrpID, actual = rrpid)
        assertContentEquals(expected = model.cardCInitResTBS.thumbs, cardCInitReqModel.thumbs)
        assertEquals(expected = model.cardCInitResTBS.lidEE, actual = cardCInitReqModel.lidEE)
        assertEquals(expected = model.cardCInitResTBS.challEE, actual = cardCInitReqModel.challEE)
        assertContentEquals(expected = model.cardCInitResTBS.caeThumb, certificate.encoded)
    }

    @Test
    fun testCheck(): Unit = runBlocking {
        repository.check(rrpid, cardCInitReqModel, model)
    }
}
