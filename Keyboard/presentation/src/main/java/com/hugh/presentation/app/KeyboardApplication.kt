package com.hugh.presentation.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KeyboardApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}