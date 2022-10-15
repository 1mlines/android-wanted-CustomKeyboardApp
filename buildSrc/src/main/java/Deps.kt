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

    object Compose {
        val activityCompose by lazy { "androidx.activity:activity-compose:1.5.0" }
        val composeUI by lazy { "androidx.compose.ui:ui:1.2.1" }
        val composeUITooling by lazy { "androidx.compose.ui:ui-tooling:1.2.1" }
        val composeFoundation by lazy { "androidx.compose.foundation:foundation:1.2.1" }
        val composeMaterial by lazy { "androidx.compose.material:material:1.2.1" }
        val composeNavigation by lazy { "androidx.navigation:navigation-compose:2.5.2" }
        val composeImmutable by lazy { "org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5" }
        val composeFlowLayout by lazy { "com.google.accompanist:accompanist-flowlayout:0.24.13-rc" }
        val composeLiveData by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1" }
        val composeMaterialIcon by lazy { "androidx.compose.material:material-icons-core:1.2.1" }
        val composeMaterialIconExtended by lazy { "androidx.compose.material:material-icons-extended:1.2.1" }
        val composeHiltNavigation by lazy {"androidx.hilt:hilt-navigation-compose:1.0.0"}

        val composeList = listOf(
            activityCompose,
            composeUI,
            composeUITooling,
            composeFoundation,
            composeMaterial,
            composeNavigation,
            composeImmutable,
            composeFlowLayout,
            composeLiveData,
            composeMaterialIcon,
            composeMaterialIconExtended,
            composeHiltNavigation
        )
    }
}
