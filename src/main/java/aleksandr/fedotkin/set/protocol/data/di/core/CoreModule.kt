package aleksandr.fedotkin.set.protocol.data.di.core

import java.security.SecureRandom
import org.koin.dsl.module

val coreModule = module {

    includes(certificateModule, signatureModule, cipherModule, messageDigestModule, keyModule)

    single {
        SecureRandom()
    }
}
