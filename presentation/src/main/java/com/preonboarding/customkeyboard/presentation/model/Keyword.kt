package com.preonboarding.customkeyboard.presentation.model

import com.preonboarding.customkeyboard.presentation.R

data class Keyword(
    val imgSource: Int,
    val title: String,
) {
    companion object {
        fun getDummyKeywords(): List<Keyword> {

            return listOf(
                Keyword(R.drawable.ic_character_funny, "신나\uD83D\uDC83"),
                Keyword(R.drawable.ic_character_expect, "기대\uD83D\uDC97"),
                Keyword(R.drawable.ic_character_play, "놀이\uD83D\uDC97")
            )
        }
    }
}
