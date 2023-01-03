package aleksandr.fedotkin.core.core.di.repositories

import org.koin.dsl.module

val repositoryTestModule = module {

    includes(
        coreRepositoryTestModule,
        generalRepositoryModule,
        errorRepositoryTestModule,
        cryptoRepositoryTestModule
    )
}
