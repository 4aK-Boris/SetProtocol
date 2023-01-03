package aleksandr.fedotkin.set.protocol.core.di.mappers

import aleksandr.fedotkin.set.protocol.data.mappers.crypto.EXHMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.oaep.OAEP2MapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.oaep.OAEP3MapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.oaep.OAEP4MapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.oaep.OAEPMapperTest
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val cryptoMapperTestModule = module {

    factoryOf(::EXHMapperTest)

    factoryOf(::OAEPMapperTest)

    factoryOf(::OAEP2MapperTest)

    factoryOf(::OAEP3MapperTest)

    factoryOf(::OAEP4MapperTest)
}
