package aleksandr.fedotkin.set.protocol.data.di.repository

import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.res.CardCInitResTBSMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req.PANOnlyMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req.RegFormReqDataMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFormResTBSMapper
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.req.CardCInitReqRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.res.CardCInitResRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.res.CardCInitResTBSRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req.PANOnlyRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req.RegFormReqDataRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req.RegFormReqRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res.ReferralDataRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res.RegFieldRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res.RegFormDataRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res.RegFormOrReferralRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res.RegFormResRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res.RegFormResTBSRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res.RegTemplateRepositoryImpl
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.req.CardCInitReqRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.res.CardCInitResRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.res.CardCInitResTBSRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.PANOnlyRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.RegFormReqDataRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.RegFormReqRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.ReferralDataRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFieldRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFormDataRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFormOrReferralRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFormResRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFormResTBSRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegTemplateRepository
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val certificateRepositoryModule = module {

    factory<CardCInitReqRepository> { CardCInitReqRepositoryImpl(mapper = get()) }

    factory<CardCInitResTBSRepository> {
        CardCInitResTBSRepositoryImpl(
            mapper = get(),
            keyRepository = get(),
            signatureRepository = get { parametersOf(get<CardCInitResTBSMapper>()) }
        )
    }

    factory<CardCInitResRepository> {
        CardCInitResRepositoryImpl(
            mapper = get(),
            cardCInitResTBSRepository = get()
        )
    }

    factory<PANOnlyRepository> {
        PANOnlyRepositoryImpl(mapper = get())
    }

    factory<RegFormReqDataRepository> {
        RegFormReqDataRepositoryImpl(mapper = get())
    }

    factory<RegFormReqRepository> {
        RegFormReqRepositoryImpl(
            mapper = get(),
            panOnlyRepository = get(),
            regFormReqDataRepository = get(),
            exhRepository = get { parametersOf(get<RegFormReqDataMapper>(), get<PANOnlyMapper>()) }
        )
    }

    factory<RegFormResRepository> {
        RegFormResRepositoryImpl(mapper = get(), regFormResTBSRepository = get())
    }

    factory<RegFormResTBSRepository> {
        val mapper = get<RegFormResTBSMapper>()
        RegFormResTBSRepositoryImpl(
            mapper = mapper,
            signatureRepository = get { parametersOf(mapper) },
            regFormOrReferralRepository = get()
        )
    }

    factory<RegFormOrReferralRepository> {
        RegFormOrReferralRepositoryImpl(mapper = get(), referralDataRepository = get(), regFormDataRepository = get())
    }

    factory<RegFieldRepository> {
        RegFieldRepositoryImpl(mapper = get())
    }

    factory<ReferralDataRepository> {
        ReferralDataRepositoryImpl(mapper = get(), regFieldRepository = get())
    }

    factory<RegFormDataRepository> {
        RegFormDataRepositoryImpl(mapper = get(), regTemplateRepository = get())
    }

    factory<RegTemplateRepository> {
        RegTemplateRepositoryImpl(mapper = get(), regFieldRepository = get())
    }
}
