package aleksandr.fedotkin.core.core.usecase

import aleksandr.fedotkin.core.core.DTO
import aleksandr.fedotkin.core.core.Model
import aleksandr.fedotkin.core.core.NUMBER_LENGTH
import aleksandr.fedotkin.core.core.repository.BaseSetRepository
import java.math.BigInteger
import java.security.PrivateKey
import java.security.PublicKey
import org.koin.core.component.KoinComponent
import kotlin.random.Random

interface BaseUseCase<T : Model, R : DTO> : KoinComponent {

    val publicKey: PublicKey

    val privateKey: PrivateKey

    var rrpid: BigInteger

    val repository: BaseSetRepository<T, R>

    fun generateNewNumber(): BigInteger {
        return BigInteger(rnd.nextBytes(NUMBER_LENGTH))
    }

    fun generateNewNumber(data: String): BigInteger {
        return BigInteger(data)
    }

    fun generateByteArray(size: Int): ByteArray {
        return rnd.nextBytes(size = size)
    }
    companion object {
        private val rnd = Random
    }
}
