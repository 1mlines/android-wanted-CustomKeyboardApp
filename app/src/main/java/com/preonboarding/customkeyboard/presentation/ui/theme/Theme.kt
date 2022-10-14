package com.preonboarding.customkeyboard.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.preonboarding.customkeyboard.ui.theme.*

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

)

object ReplacementTheme {
    val NewTypography: ReplacementMyTypography
        @Composable
        get() = LocalTypography.current
}

@Composable
fun ReplacementTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider(
        LocalTypography provides myTypography
    ){
        MaterialTheme(
            colors = colors,
            shapes = Shapes,
            content = content,
        )
    }
}