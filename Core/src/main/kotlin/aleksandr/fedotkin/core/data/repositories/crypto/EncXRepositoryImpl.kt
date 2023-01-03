package aleksandr.fedotkin.core.data.repositories.crypto

//class EncXRepositoryImpl(
//    private val encXMapper: EncXMapper,
//    private val keyRepository: KeyRepository,
//    private val signatureRepository: SignatureRepository,
//    private val cipherRepository: CipherRepository,
//    private val oaep2Repository: OAEP2Repository
//) : EncXRepository {
//
//    override val serializer = EncX.serializer()
//
//    override val convertToModel: (EncX) -> EncXModel = encXMapper::map
//
//    override val convertToDTO: (EncXModel) -> EncX = encXMapper::map
//
//    override suspend fun <T : Model, S : Model, R : DTO, K : DTO> encrypt(
//        data: T,
//        secondaryData: S,
//        privateKey: PrivateKey,
//        certificate: X509Certificate,
//        serializer: KSerializer<R>,
//        secondarySerializer: KSerializer<K>,
//        map: (T) -> R,
//        secondaryMap: (S) -> K
//    ): EncXModel {
//        val signature = signatureRepository.createSignature(
//            model = data,
//            map = map,
//            serializer = serializer,
//            privateKey = privateKey
//        )
//        val secretKey = keyRepository.generateSecretKey()
//        val cipherData = cipherRepository.symmetricEncrypt(
//            model = data,
//            map = map,
//            serializer = serializer,
//            secretKey = secretKey
//        )
//        val cipherSecondaryData = oaep2Repository.createAndEncrypt(
//            secretKey = secretKey,
//            data = secondaryData,
//            map = secondaryMap,
//            certificate = certificate,
//            serializer = secondarySerializer
//        )
//        return EncXModel(
//            signature = signature,
//            data = cipherData,
//            secretKeyAndData = cipherSecondaryData
//        )
//    }
//
//    override suspend fun <T : Model, S : Model, R : DTO, K : DTO> decrypt(
//        encXModel: EncXModel,
//        privateKey: PrivateKey,
//        certificate: X509Certificate,
//        serializer: KSerializer<R>,
//        secondarySerializer: KSerializer<K>,
//        map: (R) -> T,
//        secondaryMap: (K) -> S
//    ): Pair<T, S> {
//        val secondaryData = cipherRepository.asymmetricDecrypt(data = encXModel.secretKeyAndData, deserializer = OAEP2.serializer(), privateKey = privateKey) {
//            oaep2Repository.convertToModel(oaep2 = it, map = secondaryMap, serializer = secondarySerializer)
//        }
//        val data = cipherRepository.symmetricDecrypt(data = encXModel.data, secretKey = secondaryData.secretKey, deserializer = serializer)
//        if (!signatureRepository.verifySignature(data = data, serializer = serializer, publicKey = certificate.publicKey, signatureArray = encXModel.signature)) {
//            throw SignatureFailure()
//        }
//        return map(data) to secondaryData.p
//    }
//}
