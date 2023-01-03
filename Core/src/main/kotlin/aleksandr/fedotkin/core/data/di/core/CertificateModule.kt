package aleksandr.fedotkin.core.data.di.core

import aleksandr.fedotkin.core.core.X509
import java.security.cert.CertificateFactory
import org.koin.dsl.module

val certificateModule = module {

    single<CertificateFactory> {
        CertificateFactory.getInstance(X509)
    }
}
