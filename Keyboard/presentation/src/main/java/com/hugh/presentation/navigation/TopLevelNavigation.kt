package com.hugh.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hugh.presentation.action.compose.info.InfoAction
import com.hugh.presentation.action.compose.test.TestAction
import com.hugh.presentation.ui.info.InfoRoute
import com.hugh.presentation.ui.main.CustomKeyBoardAppState
import com.hugh.presentation.ui.test.ClipBoardTestRoute
import com.hugh.presentation.ui.test.TestRoute

/**
 * @Created by 김현국 2022/10/12
 */
internal fun NavGraphBuilder.infoGraph(
    navigateTestScreen: (InfoAction) -> Unit
) {
    navigation(
        route = NavigationRoute.InfoScreenGraph.route,
        startDestination = NavigationRoute.InfoScreenGraph.InfoScreen.route
    ) {
        composable(
            route = NavigationRoute.InfoScreenGraph.InfoScreen.route
        ) {
            InfoRoute(
                navigateTestScreen = navigateTestScreen
            )
        }
    }
}
internal fun NavGraphBuilder.keyboardTestGraph(
    navigateClipBoard: (TestAction) -> Unit
) {
    navigation(
        route = NavigationRoute.KeyBoardTestScreenGraph.route,
        startDestination = NavigationRoute.KeyBoardTestScreenGraph.KeyBoardTestScreen.route
    ) {
        composable(
            route = NavigationRoute.KeyBoardTestScreenGraph.KeyBoardTestScreen.route
        ) {
            TestRoute(
                navigateClipBoard = navigateClipBoard
            )
        }

        composable(
            route = NavigationRoute.KeyBoardTestScreenGraph.ClipBoardTestScreen.route
        ) {
            ClipBoardTestRoute(
                navigateClipBoard = navigateClipBoard
            )
        }
    }
}
