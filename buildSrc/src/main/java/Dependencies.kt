/**
 * To define plugins
 */
object BuildPlugins {
    val android by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
    val ktlint by lazy { "org.jlleitschuh.gradle.ktlint" }
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

    // https://github.com/material-components/material-components-android/releases
    val androidMaterial by lazy { "com.google.android.material:material:${Versions.androidMaterial}" }

    // https://developer.android.com/jetpack/androidx/releases/lifecycle
    val androidxLifecycle by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidMaterial}" }

    // https://developer.android.com/jetpack/androidx/releases/activity
    val androidxActivity by lazy { "androidx.activity:activity-compose:${Versions.androidxActivity}" }

    // https://developer.android.com/jetpack/androidx/releases/compose
    val composeUi by lazy { "androidx.compose.ui:ui:${Versions.compose}" }

    // https://developer.android.com/jetpack/androidx/releases/compose
    val composePreview by lazy { "androidx.compose.ui:ui-tooling-preview:${Versions.compose}" }

    // https://developer.android.com/jetpack/androidx/releases/compose
    val composeUiTest by lazy { "androidx.compose.ui:ui-test-junit4:${Versions.compose}" }

    // https://developer.android.com/jetpack/androidx/releases/compose
    val composeUiTooling by lazy { "androidx.compose.ui:ui-tooling:${Versions.compose}" }

    // https://mvnrepository.com/artifact/androidx.compose.material/material-icons-extended?repo=google
    val materialIcons by lazy { "androidx.compose.material:material-icons-extended:${Versions.matirialIcon}" }

    // https://developer.android.com/jetpack/androidx/releases/compose-material3
    val material3 by lazy { "androidx.compose.material3:material3:${Versions.matirial3}" }

    // https://developer.android.com/jetpack/androidx/releases/constraintlayout
    val composeConstaintlayout by lazy { "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstaintlayout}" }

    // https://github.com/DevSrSouza/compose-icons/releases
    val fontAwesome by lazy { "br.com.devsrsouza.compose.icons.android:font-awesome:${Versions.fontAwesome}" }
    val lineAwesome by lazy { "br.com.devsrsouza.compose.icons.android:line-awesome:${Versions.lineAwesome}" }

    // https://developer.android.com/jetpack/androidx/releases/constraintlayout
    val systemUiController by lazy { "com.google.accompanist:accompanist-systemuicontroller:${Versions.systemUiController}" }

    val commonUiTestDependencies by lazy {
        listOf(
            composeUiTest
        )
    }

    val commonUiDependencies by lazy {
        listOf(
            androidMaterial,
            androidxLifecycle,
            androidxActivity,
            composeUi,
            materialIcons,
            material3,
            composeConstaintlayout,
            fontAwesome,
            lineAwesome,
            systemUiController,
            koinCompose,
        )
    }

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