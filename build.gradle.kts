// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(BuildPlugins.ktlint) version Versions.ktlint
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
    dependencies {
        classpath(BuildPlugins.android)
        classpath(BuildPlugins.kotlin)
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.22.0")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

apply(from = "buildSrc/git-hooks.gradle")

subprojects {
    apply(from = "../plugins/Ktlint.gradle")
    apply(from = "../plugins/detekt.gradle")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
