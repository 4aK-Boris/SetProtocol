package aleksandr.fedotkin.set.protocol.core.di.repositories

import aleksandr.fedotkin.set.protocol.data.repositories.general.MessageHeaderRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.general.MessageIDRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.general.MessageWrapperRepositoryTest
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val generalRepositoryModule = module {

    factoryOf(::MessageHeaderRepositoryTest)

    factoryOf(::MessageIDRepositoryTest)

    factoryOf(::MessageWrapperRepositoryTest)
}
