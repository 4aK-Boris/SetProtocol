package aleksandr.fedotkin.core.data.di.core

import aleksandr.fedotkin.core.core.SIGNATURE_ALGORITHM
import java.security.Signature
import org.koin.dsl.module

val signatureModule = module {

    single<Signature> {
        Signature.getInstance(SIGNATURE_ALGORITHM)
    }
}
