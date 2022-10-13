package com.hugh.presentation.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hugh.presentation.R

val notosansKr = FontFamily(
    Font(R.font.notosans_kr_regular, FontWeight.Normal), // weight400
    Font(R.font.notosans_kr_bold, FontWeight.Bold), // weight700
    Font(R.font.notosans_kr_medium, FontWeight.Medium) // weight500
)
val notosansCjKr = FontFamily(
    Font(R.font.notosans_cj_kkr_regular, FontWeight.Normal),
    Font(R.font.notosans_cj_kkr_bold, FontWeight.Bold),
    Font(R.font.notosans_cj_kkr_medium, FontWeight.Medium)
)

val customKeyBoardTypography = CustomKeyBoardTypography(
    title = TextStyle(
        fontFamily = notosansCjKr,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp
    ),
    allTitle = TextStyle(
        fontFamily = notosansCjKr,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 24.sp
    ),
    allBodyMid = TextStyle(
        fontFamily = notosansKr,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    allBody = TextStyle(
        fontFamily = notosansKr,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 24.sp
    ),
    subTitle = TextStyle(
        fontFamily = notosansCjKr,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    subBody = TextStyle(
        fontFamily = notosansKr,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp
    ),
    thirdSubBody = TextStyle(
        fontFamily = notosansKr,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 14.sp
    ),
    subTitle3 = TextStyle(
        fontFamily = notosansCjKr,
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
