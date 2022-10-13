package com.hugh.presentation.action

sealed class ClipState {
    data class Clip(val text: String) : ClipState()
}