package com.hugh.presentation.action.clipAction

sealed class ClipBoardAction {
    data class Copy(val text: String) : ClipBoardAction()
    data class Paste(val text: String) : ClipBoardAction()
    data class Delete(val id: Long) : ClipBoardAction()
}
