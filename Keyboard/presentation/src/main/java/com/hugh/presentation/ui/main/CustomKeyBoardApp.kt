package com.hugh.presentation.ui.main

/**
 * @Created by 김현국 2022/10/13
 */
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import com.hugh.presentation.action.compose.InfoNavTarget
import com.hugh.presentation.action.compose.TestNavTarget
import com.hugh.presentation.action.compose.info.InfoActionActor
import com.hugh.presentation.action.compose.test.TestActionActor
import com.hugh.presentation.navigation.NavigationRoute
import com.hugh.presentation.navigation.infoGraph
import com.hugh.presentation.navigation.keyboardTestGraph
import com.hugh.presentation.ui.info.InfoScreenViewModel
import com.hugh.presentation.ui.test.TestScreenViewModel
import com.hugh.presentation.ui.theme.CustomKeyBoardTheme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

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
            val infoScreenViewModel: InfoScreenViewModel = hiltViewModel()
            val infoActor by lazy { InfoActionActor(infoScreenViewModel) }

            val testScreenViewModel: TestScreenViewModel = hiltViewModel()
            val testActor by lazy { TestActionActor(testScreenViewModel) }

            LaunchedEffect(key1 = infoScreenViewModel.navTarget) {
                infoScreenViewModel.navTarget.onEach { target ->
                    when (target) {
                        InfoNavTarget.KeyBoardTestScreen -> {
                            customKeyBoardAppState.navigateRoute(
                                NavigationRoute.KeyBoardTestScreenGraph.KeyBoardTestScreen.route
                            )
                        }
                    }
                }.launchIn(this)
            }
            LaunchedEffect(key1 = testScreenViewModel.navTarget) {
                testScreenViewModel.navTarget.onEach { target ->
                    when (target) {
                        TestNavTarget.ClipBoardTestScreen -> {
                            customKeyBoardAppState.navigateRoute(
                                NavigationRoute.KeyBoardTestScreenGraph.ClipBoardTestScreen.route
                            )
                        }
                        TestNavTarget.GoBack -> {
                            customKeyBoardAppState.navigateBackStack()
                        }
                    }
                }.launchIn(this)
            }
            NavHost(
                modifier = Modifier.padding(paddingValues = paddingValues),
                navController = customKeyBoardAppState.navController,
                startDestination = NavigationRoute.InfoScreenGraph.route
            ) {
                infoGraph(
                    navigateTestScreen = infoActor::navigationKeyBoardScreen
                )
                keyboardTestGraph(
                    navigateClipBoard = testActor::navigationClipBoardScreen
                )
            }
        }
    }
}
