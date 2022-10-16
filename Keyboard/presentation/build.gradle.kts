plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.hugh.presentation"
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.hugh.presentation"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.1"
    }
    dataBinding {
        enable = true
    }
}

dependencies {
    implementation(project(":Keyboard:data"))
    implementation(project(":Keyboard:model"))

    implementation(Deps.androidxCore)
    implementation(Deps.material)
    implementation(Deps.appCompat)
    implementation(Deps.constraintLayout)
    testImplementation(Deps.junit)
    androidTestImplementation(Deps.junit)
    androidTestImplementation(Deps.junitTest)

    Deps.Hilt.hiltList.forEach(::implementation)
    kapt(Deps.Hilt.hiltCompiler)

    implementation(Deps.activityKtx)

    Deps.Compose.composeList.forEach(::implementation)
}
