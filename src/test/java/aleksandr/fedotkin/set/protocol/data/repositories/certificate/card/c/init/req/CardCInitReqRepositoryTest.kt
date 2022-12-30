package aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.req

import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req.CardCInitReqModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.req.CardCInitReqRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject
import kotlin.test.assertEquals

class CardCInitReqRepositoryTest: BaseTestRepository<CardCInitReqModel>() {

    override val repository by inject<CardCInitReqRepository>()

    override lateinit var model: CardCInitReqModel

    val create by lazy { return@lazy repository::create }

    @Before
    override fun before() = runBlocking {
        model = create(rrpid)
    }

    @Test
    fun testCreate() = runBlocking {
        assertEquals(expected = model.rrpID, actual = rrpid)
        assertEquals(expected = model.thumbs, emptyList())
    }
}
