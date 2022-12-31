package aleksandr.fedotkin.set.protocol.core.di.repositories

import aleksandr.fedotkin.set.protocol.data.repositories.error.ErrorMsgRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.error.ErrorRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.error.ErrorTBSRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.error.SignedErrorRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.error.UnsignedErrorRepositoryTest
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val errorRepositoryTestModule = module {

    factoryOf(::ErrorMsgRepositoryTest)

    factoryOf(::ErrorTBSRepositoryTest)

    factoryOf(::SignedErrorRepositoryTest)

    factoryOf(::ErrorRepositoryTest)

    factoryOf(::UnsignedErrorRepositoryTest)
}
