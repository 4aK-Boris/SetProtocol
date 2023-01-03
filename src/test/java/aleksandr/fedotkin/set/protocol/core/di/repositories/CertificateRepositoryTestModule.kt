package aleksandr.fedotkin.set.protocol.core.di.repositories

import aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.req.CardCInitReqRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.res.CardCInitResRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.res.CardCInitResTBSRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req.PANOnlyRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req.RegFormReqDataRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req.RegFormReqRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res.ReferralDataRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res.RegFieldRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res.RegFormDataRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res.RegFormOrReferralRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res.RegFormResRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res.RegFormResTBSRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res.RegTemplateRepositoryTest
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val certificateRepositoryTestModule = module {

    factoryOf(::CardCInitReqRepositoryTest)

    factoryOf(::CardCInitResRepositoryTest)

    factoryOf(::CardCInitResTBSRepositoryTest)

    factoryOf(::PANOnlyRepositoryTest)

    factoryOf(::RegFormReqDataRepositoryTest)

    factoryOf(::RegFormReqRepositoryTest)

    factoryOf(::RegTemplateRepositoryTest)

    factoryOf(::RegFieldRepositoryTest)

    factoryOf(::ReferralDataRepositoryTest)

    factoryOf(::RegFormDataRepositoryTest)

    factoryOf(::RegFormOrReferralRepositoryTest)

    factoryOf(::RegFormResTBSRepositoryTest)

    factoryOf(::RegFormResRepositoryTest)
}
