package aleksandr.fedotkin.core.data.di.repository

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.core.mapper.SetMapper
import aleksandr.fedotkin.core.data.mappers.error.ErrorMapper
import aleksandr.fedotkin.core.data.mappers.error.ErrorTBSMapper
import aleksandr.fedotkin.core.data.repositories.error.ErrorMsgRepositoryImpl
import aleksandr.fedotkin.core.data.repositories.error.ErrorRepositoryImpl
import aleksandr.fedotkin.core.data.repositories.error.ErrorTBSRepositoryImpl
import aleksandr.fedotkin.core.data.repositories.error.SignedErrorRepositoryImpl
import aleksandr.fedotkin.core.data.repositories.error.UnsignedErrorRepositoryImpl
import aleksandr.fedotkin.core.domain.repositories.error.ErrorMsgRepository
import aleksandr.fedotkin.core.domain.repositories.error.ErrorRepository
import aleksandr.fedotkin.core.domain.repositories.error.ErrorTBSRepository
import aleksandr.fedotkin.core.domain.repositories.error.SignedErrorRepository
import aleksandr.fedotkin.core.domain.repositories.error.UnsignedErrorRepository
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val errorRepositoriesModule = module {

    factory<ErrorRepository<Model, DTO>> { (mapper: SetMapper<Model, DTO>) ->
        val errorMapper = get<ErrorMapper<Model, DTO>> { parametersOf(mapper) }
        ErrorRepositoryImpl(
            mapper = errorMapper,
            unsignedErrorRepository = get { parametersOf(mapper) },
            signedErrorRepository = get { parametersOf(mapper) },
            errorTBSRepository = get { parametersOf(mapper) },
            messageWrapperRepository = get { parametersOf(errorMapper) }
        )
    }

    factory<SignedErrorRepository<Model, DTO>> { (mapper: SetMapper<Model, DTO>) ->
        SignedErrorRepositoryImpl(
            mapper = get(),
            signatureRepository = get {
                parametersOf(get<ErrorTBSMapper<Model, DTO>> {
                    parametersOf(
                        mapper
                    )
                })
            },
        )
    }

    factory<UnsignedErrorRepository<Model, DTO>> { (mapper: SetMapper<Model, DTO>) ->
        UnsignedErrorRepositoryImpl(
            mapper = get { parametersOf(mapper) },
            errorTBSRepository = get { parametersOf(mapper) }
        )
    }

    factory<ErrorTBSRepository<Model, DTO>> { (mapper: SetMapper<Model, DTO>) ->
        ErrorTBSRepositoryImpl(
            mapper = get { parametersOf(mapper) },
            errorMsgRepository = get { parametersOf(mapper) },
            keyRepository = get()
        )
    }

    factory<ErrorMsgRepository<Model, DTO>> { (mapper: SetMapper<Model, DTO>) ->
        ErrorMsgRepositoryImpl(
            mapper = get { parametersOf(mapper) }
        )
    }
}
