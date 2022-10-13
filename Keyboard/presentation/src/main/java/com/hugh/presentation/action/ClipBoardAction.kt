package com.hugh.presentation.action

import com.hugh.model.ClipBoardState

sealed class ClipBoardAction {
    data class Copy(val text: String) : ClipBoardAction()
    data class Insert(val state: ClipBoardState) : ClipBoardAction()
    data class Delete(val id: Long) : ClipBoardAction()
}
