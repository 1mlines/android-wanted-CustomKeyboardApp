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
    val activityKtx by lazy { "androidx.activity:activity-ktx:${Versions.activityKtx}" }

    object Hilt {
        private val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.Hilt.hiltVersion}" }
        val hiltCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.Hilt.hiltVersion}" }

        val hiltList = listOf(
            hiltAndroid
        )
    }

    object Room {
        private val roomRuntime by lazy { "androidx.room:room-runtime:${Versions.Room.roomVersion}" }
        private val roomExtension by lazy { "androidx.room:room-ktx:${Versions.Room.roomVersion}" }
        val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.Room.roomVersion}" }
        val roomTest by lazy { "androidx.room:room-testing:${Versions.Room.roomVersion}" }

        val roomList = listOf(
            roomRuntime,
            roomExtension
        )
    }
}