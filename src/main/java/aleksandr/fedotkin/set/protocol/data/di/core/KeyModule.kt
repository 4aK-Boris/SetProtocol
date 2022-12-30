package aleksandr.fedotkin.set.protocol.data.di.core

import aleksandr.fedotkin.set.protocol.core.ASYMMETRIC_KEY_LENGTH
import aleksandr.fedotkin.set.protocol.core.CIPHER_ALGORITHM
import aleksandr.fedotkin.set.protocol.core.RSA
import aleksandr.fedotkin.set.protocol.core.SYMMETRIC_KEY_LENGTH
import java.security.KeyFactory
import java.security.KeyPairGenerator
import javax.crypto.KeyGenerator
import org.koin.dsl.module

val keyModule = module {

    single<KeyFactory> {
        KeyFactory.getInstance(RSA)
    }

    single<KeyGenerator> {
        KeyGenerator.getInstance(CIPHER_ALGORITHM).apply {
            init(SYMMETRIC_KEY_LENGTH, get())
        }
    }

    single<KeyPairGenerator> {
        KeyPairGenerator.getInstance(RSA).apply {
            initialize(ASYMMETRIC_KEY_LENGTH, get())
        }
    }
}
