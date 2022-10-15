package com.hugh.presentation.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hugh.presentation.ui.info.InfoRoute
import com.hugh.presentation.ui.main.CustomKeyBoardAppState
import com.hugh.presentation.ui.test.ClipBoardTestRoute
import com.hugh.presentation.ui.test.TestRoute
import com.hugh.presentation.ui.test.TestScreenViewModel

/**
 * @Created by 김현국 2022/10/12
 */
internal fun NavGraphBuilder.infoGraph(
    customKeyBoardAppState: CustomKeyBoardAppState
) {
    navigation(
        route = NavigationRoute.InfoScreenGraph.route,
        startDestination = NavigationRoute.InfoScreenGraph.InfoScreen.route
    ) {
        composable(
            route = NavigationRoute.InfoScreenGraph.InfoScreen.route
        ) {
            InfoRoute(customKeyBoardAppState)
        }
    }
}
internal fun NavGraphBuilder.keyboardTestGraph(
    customKeyBoardAppState: CustomKeyBoardAppState
) {
    navigation(
        route = NavigationRoute.KeyBoardTestScreenGraph.route,
        startDestination = NavigationRoute.KeyBoardTestScreenGraph.KeyBoardTestScreen.route
    ) {
        composable(
            route = NavigationRoute.KeyBoardTestScreenGraph.KeyBoardTestScreen.route
        ) {
            TestRoute(customKeyBoardAppState)
        }

        composable(
            route = NavigationRoute.KeyBoardTestScreenGraph.ClipBoardTestScreen.route
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                customKeyBoardAppState.navController.getBackStackEntry(NavigationRoute.KeyBoardTestScreenGraph.KeyBoardTestScreen.route)
            }
            val testScreenViewModel: TestScreenViewModel = hiltViewModel(parentEntry)
            ClipBoardTestRoute(customKeyBoardAppState,testScreenViewModel)
        }
    }
}
