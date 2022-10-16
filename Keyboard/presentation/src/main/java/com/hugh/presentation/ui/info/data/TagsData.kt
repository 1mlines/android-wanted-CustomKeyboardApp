package com.hugh.presentation.ui.info.data

import androidx.compose.runtime.Immutable

/**
 * @Created by 김현국 2022/10/12
 */

@Immutable
data class TagsData(
    val tagTitle: String
) {
    companion object {
        val List = listOf(
            TagsData(
                tagTitle = "이벤트"
            ),
            TagsData(
                tagTitle = "캐릭터"
            ),
            TagsData(
                tagTitle = "새"
            ),
            TagsData(
                tagTitle = "동물"
            ),
            TagsData(
                tagTitle = "앙증맞은"
            ),
            TagsData(
                tagTitle = "동글동글"
            ),
            TagsData(
                tagTitle = "귀여운"
            ),
            TagsData(
                tagTitle = "깜찍한"
            )
        )
    }
}