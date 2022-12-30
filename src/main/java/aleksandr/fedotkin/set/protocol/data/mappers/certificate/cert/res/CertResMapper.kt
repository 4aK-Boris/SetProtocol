package aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.res

import aleksandr.fedotkin.set.protocol.data.mappers.core.ByteArrayMapper
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.EncKMapper

class CertResMapper(
    private val byteArrayMapper: ByteArrayMapper,
    private val encKMapper: EncKMapper
) {

//    fun map(model: CertResModel): CertRes {
//        return CertRes(
//            signature = byteArrayMapper.map(byteArray = model.signature),
//            encK = encKMapper.map(model = model.encK)
//        )
//    }
//
//    fun map(dto: CertRes): CertResModel {
//        return CertResModel(
//            signature = byteArrayMapper.map(string = dto.signature),
//            encK = encKMapper.map(dto = dto.encK)
//        )
//    }
}