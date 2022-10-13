package com.hugh.presentation.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

/**
 * @Created by 김현국 2022/10/13
 */
val White = Color(0xFFFCFCFF)
val Black = Color(0xFF1C1C26)

// USED
val AllMainColor = Color(0xFFFF417D)
val AllTitleGray = Color(0xFF42444C)
val AllSubDarkGray = Color(0xFF919299)
val AllBodyGray = Color(0xFF4B4E57)
val AllBtnGray = Color(0xFFEBEDF5)
val AllBoxGray = Color(0xFFF2F3F7)
val ThemeInfoGem = Color(0xFF7DC9FC)

@Stable
class Colors(
    white: Color,
    black: Color,
    allMainColor: Color,
    allTitleGray: Color,
    allSubDarkGray: Color,
    allBodyGray: Color,
    allBtnGray: Color,
    allBoxGray: Color,
    themeInfoGem: Color

) {

    var white by mutableStateOf(white, structuralEqualityPolicy())
        internal set
    var black by mutableStateOf(black, structuralEqualityPolicy())
        internal set
    var allMainColor by mutableStateOf(allMainColor, structuralEqualityPolicy())
        internal set
    var allTitleGray by mutableStateOf(allTitleGray, structuralEqualityPolicy())
        internal set
    var allSubDarkGray by mutableStateOf(allSubDarkGray, structuralEqualityPolicy())
        internal set
    var allBodyGray by mutableStateOf(allBodyGray, structuralEqualityPolicy())
        internal set
    var allBtnGray by mutableStateOf(allBtnGray, structuralEqualityPolicy())
        internal set
    var allBoxGray by mutableStateOf(allBoxGray, structuralEqualityPolicy())
        internal set
    var themeInfoGem by mutableStateOf(themeInfoGem, structuralEqualityPolicy())
        internal set

    /**
     * Returns a copy of this Colors, optionally overriding some of the values.
     */
    fun copy(
        white: Color = this.white,
        black: Color = this.black,
        allMainColor: Color = this.allMainColor,
        allTitleGray: Color = this.allTitleGray,
        allSubDarkGray: Color = this.allSubDarkGray,
        allBodyGray: Color = this.allBodyGray,
        allBtnGray: Color = this.allBtnGray,
        allBoxGray: Color = this.allBoxGray,
        themeInfoGem: Color = this.themeInfoGem

    ): Colors = Colors(
        white,
        black,
        allMainColor,
        allTitleGray,
        allSubDarkGray,
        allBodyGray,
        allBtnGray,
        allBoxGray,
        themeInfoGem
    )

    override fun toString(): String {
        return "Colors()"
    }
}

fun lightColors(
    white: Color = White,
    black: Color = Black,
    allMainColor: Color = AllMainColor,
    allTitleGray: Color = AllTitleGray,
    allSubDarkGray: Color = AllSubDarkGray,
    allBodyGray: Color = AllBodyGray,
    allBtnGray: Color = AllBtnGray,
    allBoxGray: Color = AllBoxGray,
    themeInfoGem: Color = ThemeInfoGem

): Colors = Colors(
    white,
    black,
    allMainColor,
    allTitleGray,
    allSubDarkGray,
    allBodyGray,
    allBtnGray,
    allBoxGray,
    themeInfoGem
)

val LocalCustomKeyBoardColor = staticCompositionLocalOf {
    lightColors()
}