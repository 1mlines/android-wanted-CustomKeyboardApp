package com.hugh.presentation.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val customKeyBoardTypography = CustomKeyBoardTypography(
    title = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp
    ),
    allTitle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 24.sp
    ),
    allBodyMid = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    allBody = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 24.sp
    ),
    subTitle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    subBody = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp
    ),
    thirdSubBody = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 14.sp
    ),
    subTitle3 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 24.sp
    )
)

@Immutable
data class CustomKeyBoardTypography(
    val title: TextStyle,
    val allTitle: TextStyle,
    val allBodyMid: TextStyle,
    val allBody: TextStyle,
    val subTitle: TextStyle,
    val subBody: TextStyle,
    val thirdSubBody: TextStyle,
    val subTitle3: TextStyle
)
val LocalCustomKeyBoardTypography = staticCompositionLocalOf {
    CustomKeyBoardTypography(
        title = customKeyBoardTypography.title,
        allTitle = customKeyBoardTypography.allTitle,
        allBodyMid = customKeyBoardTypography.allBodyMid,
        allBody = customKeyBoardTypography.allBody,
        subTitle = customKeyBoardTypography.subTitle,
        subBody = customKeyBoardTypography.subBody,
        thirdSubBody = customKeyBoardTypography.thirdSubBody,
        subTitle3 = customKeyBoardTypography.subTitle3
    )
}
