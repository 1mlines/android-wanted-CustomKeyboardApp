package com.hugh.presentation.navigation

/**
 * @Created by 김현국 2022/10/13
 */
sealed class NavigationRoute(val route: String) {
    object InfoScreenGraph : NavigationRoute("info_screen") {
        object InfoScreen : NavigationRoute("info_screen.screen")
    }
    object KeyBoardTestScreenGraph : NavigationRoute("keyboard_test_screen") {
        object KeyBoardTestScreen : NavigationRoute("keyboard_test_screen.screen")
        object ClipBoardTestScreen : NavigationRoute("keyboard_test_screen.clip_board")
    }
}