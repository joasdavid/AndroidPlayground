plugins {
    kotlin("android")
    id("com.android.application")
    id("kotlin-parcelize")
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
    namespace = "pt.joasvpereira.xorganizer"
}

dependencies {
    implementation("androidx.core:core-splashscreen:1.0.0")

    implementation(project(":coreUi"))
    implementation(project(":core"))
    implementation(project(":sessionfeature"))
    implementation(project(":sessionCore"))
    implementation(project(":main"))
    implementation(project(":settings"))
}
