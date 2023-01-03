package aleksandr.fedotkin.set.protocol.data.di.core

import aleksandr.fedotkin.set.protocol.core.SIGNATURE_ALGORITHM
import java.security.Signature
import org.koin.dsl.module

val signatureModule = module {

    single<Signature> {
        Signature.getInstance(SIGNATURE_ALGORITHM)
    }
}
