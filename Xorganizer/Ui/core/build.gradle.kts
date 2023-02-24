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
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    namespace = "pt.joasvpereira.core"
}

dependencies {

    Deps.commonDependencies.forEach { dependency ->
        api(dependency)
    }

    Deps.commonTestDependencies.forEach { dependency ->
        testImplementation(dependency)
    }

    Deps.commonAndroidTestDependencies.forEach { dependency ->
        androidTestImplementation(dependency)
    }

    kapt(Deps.roomCompiler)
}