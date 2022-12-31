package aleksandr.fedotkin.set.protocol.core.di.mappers

import org.koin.core.module.dsl.factoryOf
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.req.CardCInitReqMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.res.CardCInitResMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.res.CardCInitResTBSMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req.PANOnlyMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req.RegFormReqDataMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req.RegFormReqMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.ReferralDataMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFieldMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFormDataMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFormOrReferralMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFormResMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFormResTBSMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegTemplateMapperTest
import org.koin.dsl.module

val certificateMappersTestModule = module {

    factoryOf(::CardCInitReqMapperTest)

    factoryOf(::CardCInitResTBSMapperTest)

    factoryOf(::CardCInitResMapperTest)

    factoryOf(::RegFormReqMapperTest)

    factoryOf(::RegFormReqDataMapperTest)

    factoryOf(::PANOnlyMapperTest)

    factoryOf(::RegFieldMapperTest)

    factoryOf(::ReferralDataMapperTest)

    factoryOf(::RegTemplateMapperTest)

    factoryOf(::RegFormDataMapperTest)

    factoryOf(::RegFormOrReferralMapperTest)

    factoryOf(::RegFormResTBSMapperTest)

    factoryOf(::RegFormResMapperTest)
}
