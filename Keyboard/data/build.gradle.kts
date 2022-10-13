plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.hugh.presentation"
    compileSdk = Versions.compileSdk

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":Keyboard:model"))

    Deps.Hilt.hiltList.forEach(::implementation)
    kapt(Deps.Hilt.hiltCompiler)

    Deps.Room.roomList.forEach(::implementation)
    kapt(Deps.Room.roomCompiler)
    testImplementation(Deps.Room.roomTest)
}