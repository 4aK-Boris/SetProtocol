package aleksandr.fedotkin.set.protocol.data.repositories.certificate.cert.res

//class CertResDataRepositoryImpl(
//    private val certResDataMapper: CertResDataMapper,
//    private val signatureRepository: SignatureRepository
//): CertResDataRepository {
//
//    override val serializer = CertResData.serializer()
//
//    override val convertToDTO: (CertResDataModel) -> CertResData = certResDataMapper::map
//
//    override val convertToModel: (CertResData) -> CertResDataModel = certResDataMapper::map
//
//    override suspend fun checkSignature(
//        signature: ByteArray,
//        model: CertResDataModel,
//        certificate: X509Certificate
//    ) {
//        if (signatureRepository.verifySignature(
//                model = model,
//                map = convertToDTO,
//                serializer = serializer,
//                signatureArray = signature,
//                publicKey = certificate.publicKey
//            )
//        ) {
//            throw SignatureFailure()
//        }
//    }
//}