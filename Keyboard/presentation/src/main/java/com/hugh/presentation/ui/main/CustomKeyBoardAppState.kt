package com.hugh.presentation.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

/**
 * @Created by 김현국 2022/10/12
 */

class CustomKeyBoardAppState constructor(
    val navController: NavHostController
) {

    fun navigateRoute(route: String) {
        navController.navigate(route)
    }
    fun navigateBackStack() {
        navController.popBackStack()
    }
}

@Composable
fun rememberCustomKeyBoardAppState(
    navController: NavHostController = rememberNavController()
) = remember(navController) {
    CustomKeyBoardAppState(
        navController = navController
    )
}
