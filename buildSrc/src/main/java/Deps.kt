object Deps {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinPlugin}"
    val hiltPlugin =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.Hilt.hiltVersion}"

    val androidxCore by lazy { "androidx.core:core-ktx:${Versions.core_ktx}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appcompat}" }
    val material by lazy { "com.google.android.material:material:${Versions.material}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
    val junit by lazy { "junit:junit:${Versions.junit}" }
    val junitTest by lazy { "androidx.test.ext:junit:${Versions.junitTest}" }
    val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }

    object Hilt {
        private val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.Hilt.hiltVersion}" }
        private const val hiltLifecycleViewmodel =
            "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.Hilt.hiltLifecycleViewmodelVersion}"

        val hiltCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.Hilt.hiltVersion}" }

        val hiltList = listOf(
            hiltAndroid,
//            hiltLifecycleViewmodel
        )
    }

}