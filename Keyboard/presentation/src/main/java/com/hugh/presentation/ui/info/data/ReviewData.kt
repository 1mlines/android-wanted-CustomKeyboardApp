package com.hugh.presentation.ui.info.data

/**
 * @Created by 김현국 2022/10/12
 */
data class ReviewData(
    val reviewerName: String,
    val reviewText: String,
    val date: String
) {
    companion object {
        val List = listOf(
            ReviewData(
                reviewerName = "크리에어터명",
                reviewText = "구매해주셔서 감사합니다\uD83D\uDC96",
                date = "1일"
            ),
            ReviewData(
                reviewerName = "o달빔o",
                reviewText = "아진짜 귀여워요 !!!!",
                date = "1초"
            ),
            ReviewData(
                reviewerName = "o달빔o",
                reviewText = "아진짜 귀여워요 !!!!",
                date = "3초"
            ),
            ReviewData(
                reviewerName = "o달빔o",
                reviewText = "아진짜 귀여워요 !!!!",
                date = "4초"
            ),
            ReviewData(
                reviewerName = "크리에어터명",
                reviewText = "아진짜 귀여워요 !!!!",
                date = "5초"
            )
        )
    }
}