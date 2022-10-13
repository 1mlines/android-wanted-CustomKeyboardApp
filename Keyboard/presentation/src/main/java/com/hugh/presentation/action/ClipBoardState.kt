package com.hugh.presentation.action

sealed class ClipBoardState {
    data class Copy(val text: String) : ClipBoardState()
    data class Insert(val text: String) : ClipBoardState()
    data class Delete(val id: Long) : ClipBoardState()
}
