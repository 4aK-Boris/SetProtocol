package aleksandr.fedotkin.set.protocol.core.repository

import aleksandr.fedotkin.set.protocol.core.NUMBER_LENGTH
import aleksandr.fedotkin.set.protocol.core.SIGNATURE_ALGORITHM
import aleksandr.fedotkin.set.protocol.core.TestMapper
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.di.setModule
import aleksandr.fedotkin.set.protocol.core.di.testModule
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import java.math.BigInteger
import java.security.PrivateKey
import java.security.PublicKey
import java.security.Security
import java.security.cert.X509Certificate
import java.util.*
import javax.crypto.SecretKey
import org.bouncycastle.asn1.x500.X500Name
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder
import org.junit.Before
import org.junit.Rule
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.random.Random

abstract class BaseTestRepository<T> : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(setModule, testModule)
    }

    private val keyRepository by inject<KeyRepository>()
    private val testRepository by inject<TestRepository>()
    protected val testMapper by inject<TestMapper>()

    protected lateinit var rrpid: BigInteger
    protected lateinit var publicKey: PublicKey
    protected lateinit var privateKey : PrivateKey
    protected lateinit var certificate: X509Certificate
    protected lateinit var secretKey: SecretKey

    abstract val repository: BaseRepository
    abstract var model: T

    abstract fun before()

    @Before
    fun beforeBase() {
        rrpid = generateNewNumber()
        keyRepository.generatePairKey().apply {
            publicKey = first
            privateKey = second
        }
        certificate = generateCertificate()
        secretKey = keyRepository.generateSecretKey()
    }

    fun generateNewNumber(): BigInteger {
        return BigInteger(rnd.nextBytes(NUMBER_LENGTH))
    }

    fun generateByteArray(size: Int): ByteArray {
        return rnd.nextBytes(size = size)
    }

    protected suspend fun generateTestModel(): TestModel {
        return testRepository.createModel()
    }

    protected suspend fun generateTestDTO(): aleksandr.fedotkin.set.protocol.core.Test {
        return testRepository.createDTO()
    }


    private fun generateCertificate(): X509Certificate {
        val bcProvider = BouncyCastleProvider()
        Security.addProvider(bcProvider)
        val startDate = Date()
        val endDate = Calendar.getInstance().run {
            time = startDate
            add(Calendar.YEAR, 1)
            this.time
        }
        val serialNumber = BigInteger(System.currentTimeMillis().toString())
        val x500Name = X500Name("CN=Александр Федоткин")
        val builder = JcaX509v3CertificateBuilder(x500Name, serialNumber, startDate, endDate, x500Name, publicKey)
        val contentSigner = JcaContentSignerBuilder(SIGNATURE_ALGORITHM).build(privateKey)
        val x509CertificateHolder = builder.build(contentSigner)
        return JcaX509CertificateConverter().setProvider(bcProvider).getCertificate(x509CertificateHolder)
    }

    companion object {
        private val rnd = Random
    }
}
