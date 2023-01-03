package aleksandr.fedotkin.set.protocol.data.repositories.core

import aleksandr.fedotkin.set.protocol.core.RSA
import aleksandr.fedotkin.set.protocol.core.SYMMETRIC_KEY_LENGTH
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class KeyRepositoryTest: BaseTestRepository<TestModel>() {

    override val repository by inject<KeyRepository>()

    override lateinit var model: TestModel

    @Before
    override fun before() = runBlocking {
        model = generateTestModel()
    }

    @Test
    fun testDecodePublicKey() {
        val encodedPublicKey = publicKey.encoded
        val decodedPublicKey = repository.decodePublicKey(array = encodedPublicKey)
        assertEquals(expected = decodedPublicKey, actual = publicKey)
    }

    @Test
    fun testDecodeSecretKey() {
        val encodedSecretKey = secretKey.encoded
        val decodedSecretKey = repository.decodeSecretKey(keyArray = encodedSecretKey)
        assertEquals(expected = decodedSecretKey, actual = secretKey)
    }

    @Test
    fun testDecodeCertificate() {
        val encodedCertificate = certificate.encoded
        val decodeCertificate = repository.decodeCertificate(certificate = encodedCertificate)
        assertEquals(expected = decodeCertificate, actual = certificate)
    }

    @Test
    fun testSecretKeyLength() {
        val length = repository.generateSecretKey().encoded.size * 8
        assertEquals(expected = length, actual = SYMMETRIC_KEY_LENGTH)
    }

    @Test
    fun testKeyPairLength() {
        assertEquals(expected = publicKey.algorithm, actual = RSA)
        assertEquals(expected = privateKey.algorithm, actual = RSA)
    }
}
