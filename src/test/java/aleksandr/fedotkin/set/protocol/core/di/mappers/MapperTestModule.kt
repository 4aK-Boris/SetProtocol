package aleksandr.fedotkin.set.protocol.core.di.mappers

import org.koin.dsl.module

val mapperTestModule = module {

    includes(
        coreMappersTestModule,
        generalMapperTestModule,
        errorMapperTestModule,
        certificateMappersTestModule,
        cryptoMapperTestModule
    )
}
