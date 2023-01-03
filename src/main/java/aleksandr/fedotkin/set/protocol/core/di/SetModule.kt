package aleksandr.fedotkin.set.protocol.core.di

import aleksandr.fedotkin.set.protocol.data.di.core.coreModule
import aleksandr.fedotkin.set.protocol.data.di.mapper.mappersModule
import aleksandr.fedotkin.set.protocol.data.di.repository.repositoriesModule
import aleksandr.fedotkin.set.protocol.domain.di.useCasesModule
import org.koin.dsl.module

val setModule = module {
    includes(repositoriesModule, mappersModule, coreModule, useCasesModule)
}
