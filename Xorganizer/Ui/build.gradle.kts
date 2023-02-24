// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply {
        set("compose_version", "1.3.2")
    }
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
    dependencies {
        classpath(BuildPlugins.android)
        classpath(BuildPlugins.kotlin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}