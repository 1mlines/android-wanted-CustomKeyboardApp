package com.hugh.presentation.ui.info.data

/**
 * @Created by 김현국 2022/10/12
 */
data class ThemeData(
    val emoji: String,
    val text: String,
    val count: Int
) {
    companion object {
        val List = listOf(
            ThemeData(
                emoji = "\uD83D\uDE0A",
                text = "맘에 들어요",
                count = 0
            ),
            ThemeData(
                emoji = "\uD83D\uDE0D",
                text = "심쿵했어요",
                count = 1
            ),
            ThemeData(
                emoji = "\uD83D\uDE09",
                text = "응원해요",
                count = 0
            ),
            ThemeData(
                emoji = "\uD83E\uDD23",
                text = "갖고 싶어요",
                count = 0
            )
        )
    }
}