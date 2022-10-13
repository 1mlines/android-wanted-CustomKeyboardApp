package com.hugh.presentation.ui.info.data


import com.hugh.presentation.R
import javax.annotation.concurrent.Immutable

/**
 * @Created by 김현국 2022/10/12
 */

@Immutable
data class KeyWordData(
    val image: Int,
    val text: String
) {
    companion object {
        val List = listOf(
            KeyWordData(
                image = R.drawable.img_keyword_1,
                text = "신나\uD83D\uDC83"
            ),
            KeyWordData(
                image = R.drawable.img_keyword_2,
                text = "기대\uD83D\uDC97"
            ),
            KeyWordData(
                image = R.drawable.img_keyword_3,
                text = "놀이\uD83D\uDC97"
            )
        )
    }
}