package aleksandr.fedotkin.core.data.repositories.core

import aleksandr.fedotkin.core.core.CIPHER_ALGORITHM
import aleksandr.fedotkin.core.domain.repositories.core.KeyRepository
import java.io.ByteArrayInputStream
import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.security.spec.X509EncodedKeySpec
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

class KeyRepositoryImpl(
    private val keyFactory: KeyFactory,
    private val keyPairGenerator: KeyPairGenerator,
    private val keyGenerator: KeyGenerator,
    private val certificateFactory: CertificateFactory
): KeyRepository {

    override fun decodePublicKey(array: ByteArray): PublicKey {
        return keyFactory.generatePublic(X509EncodedKeySpec(array))
    }

    override fun decodeSecretKey(keyArray: ByteArray): SecretKey {
        return SecretKeySpec(keyArray, 0, KEY_LENGTH, CIPHER_ALGORITHM)
    }

    override fun generateSecretKey(): SecretKey {
        return keyGenerator.generateKey()
    }

    override fun generatePairKey(): Pair<PublicKey, PrivateKey> {
        return keyPairGenerator.generateKeyPair().let { it.public to it.private }
    }

    override fun decodeCertificate(certificate: ByteArray): X509Certificate {
        return certificateFactory.generateCertificate(ByteArrayInputStream(certificate)) as X509Certificate
    }

    companion object {
        private const val KEY_LENGTH = 32
    }
}
