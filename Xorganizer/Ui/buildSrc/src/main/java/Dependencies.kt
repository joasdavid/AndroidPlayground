/**
 * To define plugins
 */
object BuildPlugins {
    val android by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
}

/**
 * To define dependencies
 */
object Deps {
    // https://developer.android.com/jetpack/androidx/releases/core
    val androidXCore by lazy { "androidx.core:core-ktx:${Versions.androidXCore}" }

    // https://mvnrepository.com/artifact/junit/junit
    val junit4 by lazy { "junit:junit:${Versions.jUnit}" }

    // https://developer.android.com/jetpack/androidx/releases/test
    val androidxTestExtJunit by lazy { "androidx.test.ext:junit:${Versions.androidxTestExtJunit}" }
    val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }

    // https://developer.android.com/jetpack/androidx/releases/datastore
    val dataStorePreferences by lazy { "androidx.datastore:datastore-preferences:${Versions.dataStorePreferences}" }

    // https://github.com/joasdavid/Logger/releases
    val jvpLogger by lazy { "com.github.joasdavid:Logger:${Versions.jvpLogger}" }

    // https://github.com/InsertKoinIO/koin/tags
    val koinCompose by lazy { "io.insert-koin:koin-androidx-compose:${Versions.koin}" }

    // https://developer.android.com/jetpack/androidx/releases/navigation
    val navigationCompose by lazy { "androidx.navigation:navigation-compose:${Versions.navigationCompose}" }

    // https://developer.android.com/jetpack/androidx/releases/room
    val roomRuntime by lazy { "androidx.room:room-runtime:${Versions.room}" }
    val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.room}" }
    val roomKtx by lazy { "androidx.room:room-ktx:${Versions.room}" }
    val roomTesting by lazy { "androidx.room:room-testing:${Versions.room}" }

    val commonDependencies by lazy {
        listOf(
            androidXCore,
            dataStorePreferences,
            jvpLogger,
            koinCompose,
            navigationCompose,
            roomRuntime,
            roomKtx,
        )
    }

    val commonTestDependencies by lazy {
        listOf(
            junit4,
            roomTesting,
        )
    }

    val commonAndroidTestDependencies by lazy {
        listOf(
            androidxTestExtJunit,
            espresso,
        )
    }
}