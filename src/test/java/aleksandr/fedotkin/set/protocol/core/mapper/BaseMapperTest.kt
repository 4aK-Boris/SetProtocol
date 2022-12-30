package aleksandr.fedotkin.set.protocol.core.mapper

import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.di.setModule
import aleksandr.fedotkin.set.protocol.core.di.testModule
import aleksandr.fedotkin.set.protocol.core.repository.TestRepository
import java.math.BigInteger
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.koin.core.parameter.parametersOf
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import org.koin.test.inject
import kotlin.test.assertEquals

abstract class BaseMapperTest<T, R> : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(setModule, testModule)
    }

    protected fun generateNewNumber(): BigInteger {
        return get()
    }

    protected fun generateByteArray(size: Int): ByteArray {
        return get { parametersOf(size) }
    }

    abstract val mapper: BaseMapper<T, R>

    protected val repository by inject<TestRepository>()

    protected suspend fun generateTestModel(): TestModel {
        return repository.createModel()
    }

    protected suspend fun generateTestDTO(): aleksandr.fedotkin.set.protocol.core.Test {
        return repository.createDTO()
    }

    protected val testMapper by lazy { repository.mapper }

    abstract suspend fun generateModel(): T

    open fun equals(expected: T, actual: T) {
        assertEquals(expected = expected, actual = actual)
    }

    private val title = this::class.simpleName

    @Test
    fun testMap() = runBlocking {
        val model = generateModel()
        val dto = mapper.map(value = model)
        val decodeModel = mapper.reverseMap(value = dto)
        println(title)
        equals(expected = decodeModel, actual = model)
    }
}
