package com.hugh.presentation.action

class ClipBoardActor(private val keyboardHandler: ClipBoardHandler) {

    fun copyText(text: String) {
        keyboardHandler.clipAction(ClipBoardAction.Copy(text))
    }

    fun deleteClipData(id: Long) {
        keyboardHandler.clipAction(ClipBoardAction.Delete(id))
    }
}