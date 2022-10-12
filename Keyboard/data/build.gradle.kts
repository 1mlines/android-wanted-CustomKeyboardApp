plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
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
    Deps.Hilt.hiltList.forEach(::implementation)
    annotationProcessor(Deps.Hilt.hiltCompiler)

    Deps.Room.roomList.forEach(::implementation)
    annotationProcessor(Deps.Room.roomCompiler)
    testImplementation(Deps.Room.roomTest)
}