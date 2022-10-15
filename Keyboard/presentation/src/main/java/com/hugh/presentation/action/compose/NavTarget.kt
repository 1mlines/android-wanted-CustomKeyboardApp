package com.hugh.presentation.action.compose

/**
 * @Created by 김현국 2022/10/14
 */

sealed class InfoNavTarget {
    object KeyBoardTestScreen : InfoNavTarget()
}
sealed class TestNavTarget {
    object ClipBoardTestScreen: TestNavTarget()
    object GoBack : TestNavTarget()
}
