package aleksandr.fedotkin.set.protocol.data.di.core

import java.security.MessageDigest
import org.koin.dsl.module

val messageDigestModule = module {

    single<MessageDigest> {
        MessageDigest.getInstance("SHA-256")
    }
}
