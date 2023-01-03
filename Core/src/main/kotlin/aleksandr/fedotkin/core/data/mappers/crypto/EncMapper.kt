package aleksandr.fedotkin.core.data.mappers.crypto

import aleksandr.fedotkin.core.data.mappers.core.ByteArrayMapper

class EncMapper(
    private val byteArrayMapper: ByteArrayMapper
) {

//    fun map(model: EncModel): Enc {
//        return Enc(
//            signature = byteArrayMapper.map(byteArray = model.signature),
//            secretKey = byteArrayMapper.map(byteArray = model.secretKey),
//            data = byteArrayMapper.map(byteArray = model.data)
//        )
//    }
//
//    fun map(dto: Enc): EncModel {
//        return EncModel(
//            signature = byteArrayMapper.map(string = dto.signature),
//            secretKey = byteArrayMapper.map(string  = dto.secretKey),
//            data = byteArrayMapper.map(string  = dto.data)
//        )
//    }
}
