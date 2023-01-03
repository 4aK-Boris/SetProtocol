package aleksandr.fedotkin.core.domain.repositories.crypto

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.data.dto.crypto.EncX
import aleksandr.fedotkin.core.domain.models.crypto.EncXModel
import java.security.PrivateKey
import java.security.cert.X509Certificate
import kotlinx.serialization.KSerializer

interface EncXRepository {

    val serializer: KSerializer<EncX>

    val convertToModel: (EncX) -> EncXModel

    val convertToDTO: (EncXModel) -> EncX

    suspend fun <T : Model, S : Model, R : DTO, K : DTO> encrypt(
        data: T,
        secondaryData: S,
        privateKey: PrivateKey,
        certificate: X509Certificate,
        serializer: KSerializer<R>,
        secondarySerializer: KSerializer<K>,
        map: (T) -> R,
        secondaryMap: (S) -> K
    ): EncXModel

    suspend fun <T : Model, S : Model, R : DTO, K : DTO> decrypt(
        encXModel: EncXModel,
        privateKey: PrivateKey,
        certificate: X509Certificate,
        serializer: KSerializer<R>,
        secondarySerializer: KSerializer<K>,
        map: (R) -> T,
        secondaryMap: (K) -> S
    ): Pair<T, S>
}
