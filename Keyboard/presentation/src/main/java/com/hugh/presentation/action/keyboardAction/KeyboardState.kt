package com.hugh.presentation.action.keyboardAction

import android.view.View

sealed class KeyboardState {
    data class Keyboard(val view: View) : KeyboardState()
}