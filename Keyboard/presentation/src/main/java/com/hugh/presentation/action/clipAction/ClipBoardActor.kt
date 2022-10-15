package com.hugh.presentation.action.clipAction

class ClipBoardActor(private val keyboardHandler: ClipBoardHandler) {

    fun copy(text: String) {
        keyboardHandler.clipAction(ClipBoardAction.Copy(text))
    }

    fun paste(text: String) {
        keyboardHandler.clipAction(ClipBoardAction.Paste(text))
    }

    fun deleteClipData(id: Long) {
        keyboardHandler.clipAction(ClipBoardAction.Delete(id))
    }
}