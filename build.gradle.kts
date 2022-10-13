buildscript {

    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://plugins.gradle.org/m2/") }
    }

    dependencies {
        classpath(Deps.gradle)
        classpath(Deps.kotlinPlugin)
        classpath(Deps.hiltPlugin)
    }
}

plugins {
    id("com.android.application") version Versions.gradlePlugin apply false
    id("com.android.library") version Versions.gradlePlugin apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlinPlugin apply false
    id("com.google.dagger.hilt.android") version Versions.Hilt.hiltVersion apply false
    id("org.jetbrains.kotlin.jvm") version "1.7.10" apply false
}