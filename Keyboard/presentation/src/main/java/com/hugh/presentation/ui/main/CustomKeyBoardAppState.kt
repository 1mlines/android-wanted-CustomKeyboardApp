package com.hugh.presentation.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

/**
 * @Created by 김현국 2022/10/12
 */

class CustomKeyBoardAppState constructor(
    val navController: NavHostController
) {

    private val currentRoute: String?
        get() = navController.currentDestination?.route

    fun navigateRoute(route: String) {
        navController.navigate(route)
    }
    fun navigateBackStack() {
        navController.popBackStack()
    }
}

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph = graph.startDestination!!) else graph
}

@Composable
fun rememberCustomKeyBoardAppState(
    navController: NavHostController = rememberNavController()
) = remember(navController) {
    CustomKeyBoardAppState(
        navController = navController
    )
}