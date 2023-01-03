object Dependencies {

    object Modules {

        const val core = ""
        const val exceptions = ":Exceptions"
        const val setProtocol = ":SetProtocol"
    }

    object JUnit {
        const val jUnit = "junit:junit:4.13.2"
    }

    object BouncyCastle {

        private const val version = "1.72"

        const val bcprov = "org.bouncycastle:bcprov-jdk18on:$version"
        const val bcpkix = "org.bouncycastle:bcpkix-jdk18on:$version"
        const val bcutil = "org.bouncycastle:bcutil-jdk18on:$version"
    }

    object Mockito {
        const val core = "org.mockito:mockito-core:4.10.0"
    }

    object Ktor {

        private const val version = "2.2.1"

        const val client = "io.ktor:ktor-client-android:$version"
        const val negotiation = "io.ktor:ktor-client-content-negotiation:$version"
        const val json = "io.ktor:ktor-serialization-kotlinx-json:$version"
        const val logging = "io.ktor:ktor-client-logging:$version"
        const val websockets = "io.ktor:ktor-client-websockets:$version"
    }

    object Kotlin {

        const val version = "1.7.20"

        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1"
        const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:0.4.0"
    }

    object Koin {

        private const val version = "3.3.0"

        const val koinAndroid = "io.insert-koin:koin-android:$version"
        const val koinCompose = "io.insert-koin:koin-androidx-compose:$version"
        const val koinCore = "io.insert-koin:koin-core:$version"
        const val koinKtor = "io.insert-koin:koin-ktor:$version"
        const val koinLogger = "io.insert-koin:koin-logger-slf4j:$version"
        const val koinJUnit = "io.insert-koin:koin-test-junit4:$version"
        const val koinTest = "io.insert-koin:koin-test:$version"
    }

    object Plugins {

        const val serialization = "plugin.serialization"
        const val jvm = "jvm"
    }

}
