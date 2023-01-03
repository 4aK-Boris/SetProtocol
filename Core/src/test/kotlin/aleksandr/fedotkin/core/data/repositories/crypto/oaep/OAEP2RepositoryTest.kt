package aleksandr.fedotkin.core.data.repositories.crypto.oaep

import aleksandr.fedotkin.core.core.repository.BaseTestRepository
import aleksandr.fedotkin.core.domain.models.crypto.oaep.OAEP2Model
import aleksandr.fedotkin.core.domain.repositories.crypto.oaep.OAEP2Repository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class OAEP2RepositoryTest : BaseTestRepository<OAEP2Model>() {

    override val repository by inject<OAEP2Repository>()

    override lateinit var model: OAEP2Model

    val create by lazy { return@lazy repository::create }

    private val hash = generateByteArray(size = 32)

    @Before
    override fun before() = runBlocking {
        model = create(secretKey, hash)
    }

    @Test
    fun testCreate() = runBlocking {
        assertEquals(expected = model.secretKey, actual = secretKey)
        assertContentEquals(expected = model.hash, actual = hash)
    }

    @Test
    fun testEncryptAndDecrypt() = runBlocking {
        val encryptModel = repository.encrypt(model = model, publicKey = publicKey)
        val decryptModel = repository.decrypt(data = encryptModel, privateKey = privateKey)
        assertEquals(expected = decryptModel.secretKey, actual = secretKey)
        assertContentEquals(expected = model.hash, actual = hash)
    }
}
