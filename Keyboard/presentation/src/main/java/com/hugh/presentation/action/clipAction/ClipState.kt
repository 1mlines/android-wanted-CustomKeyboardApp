package com.hugh.presentation.action.clipAction

sealed class ClipState {
    data class Clip(val text: String) : ClipState()
}