plugins {
    kotlin(Dependencies.Plugins.jvm) version Dependencies.Kotlin.version
    kotlin(Dependencies.Plugins.serialization) version Dependencies.Kotlin.version
}

dependencies {

    implementation(Dependencies.Koin.koinCore)

    implementation(Dependencies.Kotlin.serialization)
    implementation(Dependencies.Kotlin.coroutines)
    implementation(Dependencies.Kotlin.datetime)

    implementation(Dependencies.BouncyCastle.bcpkix)
    implementation(Dependencies.BouncyCastle.bcprov)
    implementation(Dependencies.BouncyCastle.bcutil)

    testImplementation(Dependencies.Koin.koinJUnit)
    testImplementation(Dependencies.Koin.koinTest)
    testImplementation(Dependencies.Mockito.core)
}
