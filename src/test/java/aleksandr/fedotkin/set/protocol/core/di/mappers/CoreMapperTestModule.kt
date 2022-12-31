package aleksandr.fedotkin.set.protocol.core.di.mappers

import aleksandr.fedotkin.set.protocol.data.mappers.core.Base64MapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.core.ByteArrayMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.core.DateMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.core.DateTimeMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.core.json.JsonByteArrayMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.core.json.JsonMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.core.json.JsonModelByteArrayMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.core.json.JsonModelMapperTest
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val coreMappersTestModule = module {

    factoryOf(::JsonMapperTest)

    factoryOf(::JsonByteArrayMapperTest)

    factoryOf(::JsonModelMapperTest)

    factoryOf(::JsonModelByteArrayMapperTest)

    factoryOf(::BigIntegerMapperTest)

    factoryOf(::ByteArrayMapperTest)

    factoryOf(::DateMapperTest)

    factoryOf(::DateTimeMapperTest)

    factoryOf(::Base64MapperTest)
}
