package aleksandr.fedotkin.core.data.repositories.crypto.oaep

import aleksandr.fedotkin.core.core.repository.BaseTestRepository
import aleksandr.fedotkin.core.domain.models.crypto.oaep.OAEPModel
import aleksandr.fedotkin.core.domain.repositories.crypto.oaep.OAEPRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject
import kotlin.test.assertEquals

class OAEPRepositoryTest : BaseTestRepository<OAEPModel>() {

    override val repository by inject<OAEPRepository>()

    override lateinit var model: OAEPModel

    val create by lazy { return@lazy repository::create }

    @Before
    override fun before() = runBlocking {
        model = create(secretKey)
    }

    @Test
    fun testCreate() = runBlocking {
        assertEquals(expected = model.secretKey, actual = secretKey)
    }

    @Test
    fun testEncryptAndDecrypt() = runBlocking {
        val encryptModel = repository.encrypt(model = model, publicKey = publicKey)
        val decryptModel = repository.decrypt(data = encryptModel, privateKey = privateKey)
        assertEquals(expected = decryptModel.secretKey, actual = secretKey)
    }
}
