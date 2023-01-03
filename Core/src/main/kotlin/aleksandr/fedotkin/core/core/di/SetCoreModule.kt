package aleksandr.fedotkin.core.core.di

import aleksandr.fedotkin.core.data.di.core.coreModule
import aleksandr.fedotkin.core.data.di.mapper.mappersModule
import aleksandr.fedotkin.core.data.di.repository.repositoriesModule
import org.koin.dsl.module

val setCoreModule = module {
    includes(repositoriesModule, mappersModule, coreModule)
}
