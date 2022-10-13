package com.hugh.presentation.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle

/**
 * @Created by 김현국 2022/10/12
 */
@Composable
fun MultiStyleText(style: TextStyle, text1: String, color1: Color, text2: String, color2: Color) {
    Text(
        style = style,
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = color1)) {
                append(text1)
            }
            withStyle(style = SpanStyle(color = color2)) {
                append(text2)
            }
        },
        textAlign = TextAlign.Center
    )
}