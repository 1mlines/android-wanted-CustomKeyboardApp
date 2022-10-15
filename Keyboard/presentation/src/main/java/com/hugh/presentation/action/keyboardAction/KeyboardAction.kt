package com.hugh.presentation.action.keyboardAction

sealed class KeyboardAction {
    data class NavigateNumberKeyboard(val block: (KeyboardState) -> Unit) : KeyboardAction()
    data class NavigateClipKeyboard(val block: (KeyboardState) -> Unit) : KeyboardAction()
}
