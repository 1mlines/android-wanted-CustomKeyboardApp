package com.hugh.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun CustomKeyBoardTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        lightColors()
    } else {
        lightColors()
    }

    CompositionLocalProvider(
        LocalCustomKeyBoardTypography provides customKeyBoardTypography,
        LocalCustomKeyBoardColor provides colors
    ) {
        MaterialTheme(
            shapes = Shapes,
            content = content
        )
    }
}

object CustomKeyBoardTheme {
    val typography: CustomKeyBoardTypography
        @Composable
        get() = LocalCustomKeyBoardTypography.current

    val color: Colors
        @Composable
        get() = LocalCustomKeyBoardColor.current
}