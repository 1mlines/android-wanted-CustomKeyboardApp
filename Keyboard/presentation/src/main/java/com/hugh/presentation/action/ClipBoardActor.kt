package com.hugh.presentation.action

class ClipBoardActor(private val keyboardHandler: ClipBoardHandler) {

    fun copyText(text: String) {
        keyboardHandler.clipAction(ClipBoardState.Copy(text))
    }

    fun insertClipData(text: String) {
        keyboardHandler.clipAction(ClipBoardState.Insert(text))
    }
}