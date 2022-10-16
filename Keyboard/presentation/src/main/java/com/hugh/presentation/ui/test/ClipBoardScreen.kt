package com.hugh.presentation.ui.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.hugh.presentation.action.compose.TestNavTarget
import com.hugh.presentation.action.compose.test.TestAction
import com.hugh.presentation.action.compose.test.TestActionActor
import com.hugh.presentation.ui.component.SearchBar
import com.hugh.presentation.ui.component.Tag
import com.hugh.presentation.ui.info.data.TagsData
import com.hugh.presentation.ui.main.CustomKeyBoardAppState
import com.hugh.presentation.ui.theme.CustomKeyBoardTheme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * @Created by 김현국 2022/10/13
 */

@Composable
fun ClipBoardTestRoute(
    customKeyBoardAppState: CustomKeyBoardAppState,
    testScreenViewModel: TestScreenViewModel
) {
    val testActionActor by lazy { TestActionActor(testScreenViewModel) }
    LaunchedEffect(key1 = testScreenViewModel.navTarget) {
        testScreenViewModel.navTarget.onEach { state ->
            when (state) {
                TestNavTarget.GoBack -> {
                    customKeyBoardAppState.navigateBackStack()
                }
                else -> {}
            }
        }.launchIn(this)
    }
    ClipBoardTestScreen(
        navigateClipBoard = testActionActor::navigationClipBoardScreen
    )
}

@Composable
fun ClipBoardTestScreen(
    navigateClipBoard: (TestAction) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(start = 10.dp)
        ) {
            SearchBar(
                navigate = { navigateClipBoard(TestAction.GoBack) },
                placeHolderText = "테마 검색"
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "지금 인기 있는 검색어",
                style = CustomKeyBoardTheme.typography.allBodyMid,
                color = CustomKeyBoardTheme.color.allSubDarkGray
            )
            Spacer(modifier = Modifier.height(10.dp))
            FlowRow(
                mainAxisSize = SizeMode.Expand,
                mainAxisAlignment = FlowMainAxisAlignment.Start,
                crossAxisSpacing = 8.dp,
                mainAxisSpacing = 4.12.dp

            ) {
                TagsData.List.forEach {
                    Tag(
                        tagText = it.tagTitle,
                        color = CustomKeyBoardTheme.color.allSubDarkGray,
                        style = CustomKeyBoardTheme.typography.allBodyMid.copy(
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}
