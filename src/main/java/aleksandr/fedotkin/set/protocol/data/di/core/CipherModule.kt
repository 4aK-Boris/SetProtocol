package aleksandr.fedotkin.set.protocol.data.di.core

import aleksandr.fedotkin.set.protocol.core.CIPHER_ALGORITHM
import aleksandr.fedotkin.set.protocol.core.RSA
import javax.crypto.Cipher
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val cipherModule = module {

    single(qualifier = qualifier(name = RSA)) {
        Cipher.getInstance(RSA)
    }

    single(qualifier = qualifier(name = CIPHER_ALGORITHM)) {
        Cipher.getInstance(CIPHER_ALGORITHM)
    }
}
