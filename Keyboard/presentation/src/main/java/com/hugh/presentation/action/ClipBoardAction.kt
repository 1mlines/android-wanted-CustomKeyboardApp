package com.hugh.presentation.action

sealed class ClipBoardAction {
    data class Copy(val text: String) : ClipBoardAction()
    data class Delete(val id: Long) : ClipBoardAction()
}
