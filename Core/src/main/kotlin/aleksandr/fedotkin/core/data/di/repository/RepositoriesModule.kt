package aleksandr.fedotkin.core.data.di.repository

import org.koin.dsl.module

val repositoriesModule = module {

    includes(
        coreRepositoriesModule,
        errorRepositoriesModule,
        messageWrapperRepositoryModule,
        cryptoRepositoriesModule
    )
}
