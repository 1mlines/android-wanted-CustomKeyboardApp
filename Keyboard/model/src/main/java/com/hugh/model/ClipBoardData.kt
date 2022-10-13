package com.hugh.model

data class ClipBoardData(
    val id: Long,
    val text: String
) {

    companion object {
        val EMPTY = ClipBoardData(
            id = 0,
            text = ""
        )
    }
}
