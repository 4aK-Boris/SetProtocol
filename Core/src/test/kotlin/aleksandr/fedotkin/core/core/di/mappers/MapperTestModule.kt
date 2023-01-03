package aleksandr.fedotkin.core.core.di.mappers

import org.koin.dsl.module

val mapperTestModule = module {

    includes(
        coreMappersTestModule,
        generalMapperTestModule,
        errorMapperTestModule,
        cryptoMapperTestModule
    )
}
