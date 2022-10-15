package com.hugh.presentation.ui.main

/**
 * @Created by 김현국 2022/10/13
 */
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.hugh.presentation.navigation.NavigationRoute
import com.hugh.presentation.navigation.infoGraph
import com.hugh.presentation.navigation.keyboardTestGraph
import com.hugh.presentation.ui.theme.CustomKeyBoardTheme

/**
 * @Created by 김현국 2022/10/12
 */

@Composable
fun CustomKeyBoardApp() {
    val customKeyBoardAppState = rememberCustomKeyBoardAppState()

    Scaffold(
        backgroundColor = CustomKeyBoardTheme.color.white
    ) { paddingValues ->
        Box {
            NavHost(
                modifier = Modifier.padding(paddingValues = paddingValues),
                navController = customKeyBoardAppState.navController,
                startDestination = NavigationRoute.InfoScreenGraph.route
            ) {
                infoGraph(customKeyBoardAppState)
                keyboardTestGraph(customKeyBoardAppState)
            }
        }
    }
}
