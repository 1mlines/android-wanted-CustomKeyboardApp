package com.preonboarding.customkeyboard.presentation.model

data class Review(
    val userName: String,
    val content: String,
    val time: String,
    val isCreator: Boolean,
) {
    companion object {
        fun getDummyReviews(): List<Review> {

            return listOf(
                Review("크리에이터명", "구매해주셔서 감사합니다\uD83D\uDC96", "1일", true),
                Review("o달빔o", "아진짜 귀여워요 !!!!", "1초", false),
                Review("o달빔o", "아진짜 귀여워요 !!!!", "1분", false),
                Review("o달빔o", "아진짜 귀여워요 !!!!", "1초", false),
            )
        }
    }
}
