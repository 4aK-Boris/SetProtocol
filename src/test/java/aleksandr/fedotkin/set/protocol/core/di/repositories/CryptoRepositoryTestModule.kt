package aleksandr.fedotkin.set.protocol.core.di.repositories

import aleksandr.fedotkin.set.protocol.data.repositories.crypto.EXHRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.crypto.OAEP2RepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.crypto.OAEP3RepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.crypto.OAEP4RepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.crypto.OAEPRepositoryTest
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val cryptoRepositoryTestModule = module {

    factoryOf(::OAEPRepositoryTest)

    factoryOf(::OAEP2RepositoryTest)

    factoryOf(::OAEP3RepositoryTest)

    factoryOf(::OAEP4RepositoryTest)

    factoryOf(::EXHRepositoryTest)
}
