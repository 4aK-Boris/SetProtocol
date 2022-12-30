package aleksandr.fedotkin.set.protocol.data.di

import aleksandr.fedotkin.set.protocol.core.TestMapper
import aleksandr.fedotkin.set.protocol.core.di.setModule
import aleksandr.fedotkin.set.protocol.core.di.testModule
import org.junit.Rule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.check.checkModules
import org.koin.test.inject
import org.koin.test.verify.verify

class DiTest: KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(setModule)
    }

    @Test
    fun testDI() {
        koinApplication {
            modules(setModule)
            checkModules()
        }
    }

    @Test
    fun testTestDI() {
        koinApplication {
            modules(testModule, setModule)
            checkModules()
        }
    }

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun verify() {
        val mapper by inject<TestMapper>()
        setModule.verify()
    }
}