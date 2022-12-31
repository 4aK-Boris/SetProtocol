package aleksandr.fedotkin.set.protocol.core.di.repositories

import aleksandr.fedotkin.set.protocol.data.repositories.core.AsymmetricCipherRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.core.KeyRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.core.MessageDigestRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.core.SignatureRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.core.SymmetricCipherRepositoryTest
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val coreRepositoryTestModule = module {

    factoryOf(::AsymmetricCipherRepositoryTest)

    factoryOf(::KeyRepositoryTest)

    factoryOf(::MessageDigestRepositoryTest)

    factoryOf(::SignatureRepositoryTest)

    factoryOf(::SymmetricCipherRepositoryTest)
}
