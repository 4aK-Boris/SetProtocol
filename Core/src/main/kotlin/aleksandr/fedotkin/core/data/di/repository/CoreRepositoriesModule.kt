package aleksandr.fedotkin.core.data.di.repository

import aleksandr.fedotkin.core.core.CIPHER_ALGORITHM
import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.core.RSA
import aleksandr.fedotkin.core.core.mapper.SetMapper
import aleksandr.fedotkin.core.data.repositories.core.AsymmetricCipherRepositoryImpl
import aleksandr.fedotkin.core.domain.repositories.core.KeyRepository
import aleksandr.fedotkin.core.domain.repositories.core.MessageDigestRepository
import aleksandr.fedotkin.core.data.repositories.core.KeyRepositoryImpl
import aleksandr.fedotkin.core.data.repositories.core.MessageDigestRepositoryImpl
import aleksandr.fedotkin.core.data.repositories.core.SignatureRepositoryImpl
import aleksandr.fedotkin.core.data.repositories.core.SymmetricCipherRepositoryImpl
import aleksandr.fedotkin.core.domain.repositories.core.AsymmetricCipherRepository
import aleksandr.fedotkin.core.domain.repositories.core.SignatureRepository
import aleksandr.fedotkin.core.domain.repositories.core.SymmetricCipherRepository
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coreRepositoriesModule = module {

    factory<KeyRepository> {
        KeyRepositoryImpl(
            keyFactory = get(),
            keyPairGenerator = get(),
            keyGenerator = get(),
            certificateFactory = get()
        )
    }

    factory<MessageDigestRepository<Model, DTO>> { (mapper: SetMapper<Model, DTO>) ->
        MessageDigestRepositoryImpl(
            messageDigest = get(),
            jsonByteArrayMapper = get { parametersOf(mapper) },
            jsonModelByteArrayMapper = get { parametersOf(mapper) }
        )
    }

    factory<SignatureRepository<Model, DTO>> { (mapper: SetMapper<Model, DTO>) ->
        SignatureRepositoryImpl(
            signature = get(),
            secureRandom = get(),
            jsonByteArrayMapper = get { parametersOf(mapper) },
            jsonModelByteArrayMapper = get { parametersOf(mapper) }
        )
    }

    factory<SymmetricCipherRepository<Model, DTO>> { (mapper: SetMapper<Model, DTO>) ->
        SymmetricCipherRepositoryImpl(
            cipher = get(qualifier = named(CIPHER_ALGORITHM)),
            jsonByteArrayMapper = get { parametersOf(mapper) },
            jsonModelByteArrayMapper = get { parametersOf(mapper) }
        )
    }

    factory<AsymmetricCipherRepository<Model, DTO>> { (mapper: SetMapper<Model, DTO>) ->
        AsymmetricCipherRepositoryImpl(
            cipher = get(qualifier = named(RSA)),
            jsonByteArrayMapper = get { parametersOf(mapper) },
            jsonModelByteArrayMapper = get { parametersOf(mapper) }
        )
    }
}
