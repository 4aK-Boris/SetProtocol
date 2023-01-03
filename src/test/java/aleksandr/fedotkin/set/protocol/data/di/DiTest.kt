package aleksandr.fedotkin.set.protocol.data.di

import aleksandr.fedotkin.set.protocol.core.TestMapper
import aleksandr.fedotkin.set.protocol.core.di.setModule
import aleksandr.fedotkin.set.protocol.core.di.testModule
import aleksandr.fedotkin.set.protocol.core.mapper.BaseMapper
import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.di.core.coreModule
import java.security.PrivateKey
import java.security.PublicKey
import java.security.cert.X509Certificate
import org.junit.Rule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.check.checkKoinModules
import org.koin.test.get
import org.koin.test.mock.MockProviderRule
import org.koin.test.verify.verify
import org.mockito.Mockito

class DiTest : KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun verifyCoreModule() {
        coreModule.verify(extraTypes = listOf(ByteArray::class))
    }

    @Test
    fun checkCoreModule() {
        checkKoinModules(listOf(coreModule))
    }

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun verifySetModule() {
        setModule.verify(
            extraTypes = listOf(
                ByteArray::class,
                SetMapper::class,
                BaseMapper::class,
                X509Certificate::class,
                PrivateKey::class,
                PublicKey::class
            )
        )
    }

    @Test
    fun checkSetModule() {
        checkKoinModules(listOf(setModule)) {
            withInstance(TestMapper(bigIntegerMapper = get(), base64Mapper = get()))
        }
    }

    @Test
    fun checkDI() {
        checkKoinModules(listOf(setModule, testModule))
    }

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun verifyTestModule() {
        testModule.verify()
    }
}
