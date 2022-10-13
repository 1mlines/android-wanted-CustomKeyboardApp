package com.hugh.model

data class ClipBoardState(
    val id: Long,
    val text: String
) {

    companion object {
        val EMPTY = ClipBoardState(
            id = 0,
            text = ""
        )
    }
}
