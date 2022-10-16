package com.preonboarding.customkeyboard.presentation.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.preonboarding.customkeyboard.R

val NotoSansKR = FontFamily(
    Font(R.font.noto_sanskr_bold, FontWeight.Bold),
    Font(R.font.noto_sanskr_black, FontWeight.Black),
    Font(R.font.noto_sanskr_light, FontWeight.Light),
    Font(R.font.noto_sanskr_medium, FontWeight.Medium),
    Font(R.font.noto_sanskr_regular, FontWeight.Normal),
    Font(R.font.noto_sanskr_thin, FontWeight.Thin)
)

val LocalTypography = staticCompositionLocalOf {
    ReplacementMyTypography(
        title_20_bold = myTypography.title_20_bold,
        title_16_bold = myTypography.title_16_bold,
        title_14 = myTypography.title_14,
        body_16 = myTypography.body_16,
        body_14 = myTypography.body_14,
        body_12 = myTypography.body_12,
        body_10 = myTypography.body_10,
        sub_title = myTypography.sub_title
    )
}

// Set of Material typography styles to start with
val myTypography = ReplacementMyTypography(
    title_20_bold = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),
    body_14 = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    title_16_bold = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
    body_12 = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
    title_14 = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
    ),
    body_16 = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    sub_title = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    body_10 = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
    )
)


