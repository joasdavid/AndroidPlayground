plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-kapt")
}

@Suppress("UnstableApiUsage")
android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildFeatures {
        compose = true
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    namespace = "pt.joasvpereira.coreui"
}

dependencies {
    implementation(Deps.jvpLogger)

    // need to convert all project to Material 3 and then remove this dependency
    implementation("androidx.compose.material:material:1.3.1")

    debugApi(Deps.composePreview)

    Deps.commonUiDependencies.forEach { dependency ->
        api(dependency)
    }

    Deps.commonUiTestDependencies.forEach { dependency ->
        androidTestImplementation(dependency)
    }

    api(project(":settingsCore"))
}
