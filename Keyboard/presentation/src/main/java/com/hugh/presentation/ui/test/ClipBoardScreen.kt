package com.hugh.presentation.ui.test

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hugh.presentation.ui.component.SearchBar

/**
 * @Created by 김현국 2022/10/13
 */

@Composable
fun ClipBoardTestRoute() {
    ClipBoardTestScreen()
}

@Composable
fun ClipBoardTestScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchBar()
    }
}