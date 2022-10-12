object Deps {
    val androidxCore by lazy { "androidx.core:core-ktx:${Versions.core_ktx}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appcompat}" }
    val material by lazy { "com.google.android.material:material:${Versions.material}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
    val junit by lazy { "junit:junit:4.13.2l:${Versions.junit}" }
    val junitTest by lazy { "androidx.test.ext:junit:${Versions.junitTest}" }
    val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }
}