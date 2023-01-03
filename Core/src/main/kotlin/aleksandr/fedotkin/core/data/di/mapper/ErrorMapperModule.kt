package aleksandr.fedotkin.core.data.di.mapper

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.core.mapper.SetMapper
import aleksandr.fedotkin.core.data.mappers.error.ErrorMapper
import aleksandr.fedotkin.core.data.mappers.error.ErrorMsgMapper
import aleksandr.fedotkin.core.data.mappers.error.ErrorTBSMapper
import aleksandr.fedotkin.core.data.mappers.error.SignedErrorMapper
import aleksandr.fedotkin.core.data.mappers.error.UnsignedErrorMapper
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val errorMapperModule = module {

    factory { (mapper: SetMapper<Model, DTO>) ->
        ErrorMapper(
            signedErrorMapper = get(),
            unsignedErrorMapper = get { parametersOf(mapper) },
            mapper = mapper
        )
    }

    factory { (mapper: SetMapper<Model, DTO>) ->
        ErrorTBSMapper(
            base64Mapper = get(),
            bigIntegerMapper = get(),
            mapper = mapper,
            errorMsgMapper = get { parametersOf(mapper) }
        )
    }

    factory { (mapper: SetMapper<Model, DTO>) ->
        ErrorMsgMapper(
            messageHeaderMapper = get(),
            mapper = mapper
        )
    }

    factory {
        SignedErrorMapper(base64Mapper = get())
    }

    factory { (mapper: SetMapper<Model, DTO>) ->
        UnsignedErrorMapper(
            errorTBSMapper = get { parametersOf(mapper) }, mapper = mapper
        )
    }
}
