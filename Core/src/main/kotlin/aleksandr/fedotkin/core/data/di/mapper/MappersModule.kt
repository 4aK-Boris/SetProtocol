package aleksandr.fedotkin.core.data.di.mapper

import org.koin.dsl.module

val mappersModule = module {

    includes(
        coreMapperModule,
        errorMapperModule,
        generalMapperModule,
        cryptoMapperModule
    )
}
