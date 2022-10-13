package com.hugh.presentation.action

sealed class ClipBoardAction {
    data class Copy(val text: String) : ClipBoardAction()
    data class Insert(val state: ClipState.Clip) : ClipBoardAction()
    data class Delete(val id: Long) : ClipBoardAction()
}
