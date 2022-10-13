package com.hugh.presentation.ui.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
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

/**
 * @Created by 김현국 2022/10/13
 */

@Composable
fun TestRoute(
    navigateClipBoard: () -> Unit
) {
    TestScreen(
        navigateClipBoard = navigateClipBoard
    )
}

@Composable
fun TestScreen(
    navigateClipBoard: () -> Unit
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
            Button(
                onClick = navigateClipBoard
            ) {
                Text(text = "클립보드 Test 화면 이동")
            }
        }
    }
}
