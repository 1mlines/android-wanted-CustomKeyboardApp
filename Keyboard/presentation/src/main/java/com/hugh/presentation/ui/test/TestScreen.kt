package com.hugh.presentation.ui.test

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hugh.presentation.action.compose.TestNavTarget
import com.hugh.presentation.action.compose.test.TestAction
import com.hugh.presentation.action.compose.test.TestActionActor
import com.hugh.presentation.navigation.NavigationRoute
import com.hugh.presentation.ui.main.CustomKeyBoardAppState
import com.hugh.presentation.ui.theme.CustomKeyBoardTheme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * @Created by 김현국 2022/10/13
 */

@Composable
fun TestRoute(
    customKeyBoardAppState: CustomKeyBoardAppState,
    testScreenViewModel: TestScreenViewModel = hiltViewModel()
) {
    val testActor by lazy { TestActionActor(testScreenViewModel) }
    LaunchedEffect(key1 = testScreenViewModel.navTarget) {
        testScreenViewModel.navTarget.onEach { state ->
            when (state) {
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
    TestScreen(
        navigateClipBoard = testActor::navigationClipBoardScreen
    )
}

@Composable
fun TestScreen(
    navigateClipBoard: (TestAction) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        var text by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                modifier = Modifier,
                value = text,
                onValueChange = { text = it },
                label = { Text("테스트 입력창") }
            )
            Box(
                modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 20.dp)
                    .clickable {
                        navigateClipBoard(
                            TestAction.NavigationClipBoard
                        )
                    }
                    .background(
                        color = CustomKeyBoardTheme.color.allMainColor,
                        shape = RoundedCornerShape(20.dp)
                    )
            ) {
                Text(
                    modifier = Modifier
                        .padding(vertical = 20.dp, horizontal = 20.dp)
                        .align(Alignment.Center),
                    text = "클립보드 Test 화면 이동",
                    color = CustomKeyBoardTheme.color.white
                )
            }
        }
    }
}
