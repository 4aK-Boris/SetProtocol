package aleksandr.fedotkin.set.protocol.core.repository

import aleksandr.fedotkin.set.protocol.core.NUMBER_LENGTH
import java.math.BigInteger
import org.koin.core.component.KoinComponent
import kotlin.random.Random

interface BaseRepository: KoinComponent {
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
