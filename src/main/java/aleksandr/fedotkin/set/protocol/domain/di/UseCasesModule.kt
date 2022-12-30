package aleksandr.fedotkin.set.protocol.domain.di

import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.req.CardCInitReqMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.res.CardCInitResMapper
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import aleksandr.fedotkin.set.protocol.domain.useceses.BuyerCertificateUseCase
import aleksandr.fedotkin.set.protocol.domain.useceses.client.CardCInitReqClientUseCase
import aleksandr.fedotkin.set.protocol.domain.useceses.client.CardCInitResClientUseCase
import aleksandr.fedotkin.set.protocol.domain.useceses.server.CardCInitReqServerUseCase
import aleksandr.fedotkin.set.protocol.domain.useceses.server.CardCInitResServerUseCase
import java.security.PrivateKey
import java.security.cert.X509Certificate
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val useCasesModule = module {

    factory {
        BuyerCertificateUseCase(
            cardCInitReqUseCase = get(),
            cardCInitResUseCase = get()
        )
    }

    factory {
        val keyRepository = get<KeyRepository>()
        val (publicKey, privateKey) = keyRepository.generatePairKey()
        CardCInitReqClientUseCase(
            repository = get(),
            privateKey = privateKey,
            publicKey = publicKey,
            messageWrapperRepository = get { parametersOf(get<CardCInitReqMapper>()) })
    }

    factory { (certificate: X509Certificate, privateKey: PrivateKey) ->
        CardCInitReqServerUseCase(
            repository = get(),
            certificate = certificate,
            privateKey = privateKey,
            messageWrapperRepository = get { parametersOf(get<CardCInitReqMapper>()) }
        )
    }

    factory {
        val keyRepository = get<KeyRepository>()
        val (publicKey, privateKey) = keyRepository.generatePairKey()
        CardCInitResClientUseCase(
            repository = get(),
            privateKey = privateKey,
            publicKey = publicKey,
            messageWrapperRepository = get { parametersOf(get<CardCInitResMapper>()) })
    }

    factory { (certificate: X509Certificate, privateKey: PrivateKey) ->
        CardCInitResServerUseCase(
            repository = get(),
            certificate = certificate,
            privateKey = privateKey,
            messageWrapperRepository = get { parametersOf(get<CardCInitResMapper>()) }
        )
    }
}
