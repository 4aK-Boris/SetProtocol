package aleksandr.fedotkin.core.data.di.mapper

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.core.mapper.SetMapper
import aleksandr.fedotkin.core.data.mappers.general.MessageHeaderMapper
import aleksandr.fedotkin.core.data.mappers.general.MessageIDMapper
import aleksandr.fedotkin.core.data.mappers.general.MessageWrapperMapper
import org.koin.dsl.module

val generalMapperModule = module {

    factory {
        MessageIDMapper(
            bigIntegerMapper = get()
        )
    }

    factory { (mapper: SetMapper<Model, DTO>) ->
        MessageWrapperMapper(
            messageHeaderMapper = get(),
            mapper = mapper
        )
    }

    factory {
        MessageHeaderMapper(
            messageIDMapper = get(),
            bigIntegerMapper = get(),
            dateTimeMapper = get()
        )
    }
}
