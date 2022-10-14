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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hugh.presentation.action.compose.test.TestAction
import com.hugh.presentation.ui.theme.CustomKeyBoardTheme

/**
 * @Created by 김현국 2022/10/13
 */

@Composable
fun TestRoute(
    navigateClipBoard: (TestAction) -> Unit
) {
    TestScreen(
        navigateClipBoard = navigateClipBoard
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
            modifier = Modifier.padding(top = 20.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                modifier = Modifier,
                value = text,
                onValueChange = { text = it },
                label = { Text("테스트 입력창") }
            )
            Box(
                modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp).clickable {
                    navigateClipBoard(
                        TestAction.NavigationClipBoard
                    )
                }.background(color = CustomKeyBoardTheme.color.allMainColor, shape = RoundedCornerShape(20.dp))
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp).align(Alignment.Center),
                    text = "클립보드 Test 화면 이동",
                    color = CustomKeyBoardTheme.color.white
                )
            }
        }
    }
}
