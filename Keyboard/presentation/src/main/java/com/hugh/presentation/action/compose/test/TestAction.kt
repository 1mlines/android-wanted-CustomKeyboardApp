package com.hugh.presentation.action.compose.test

/**
 * @Created by 김현국 2022/10/14
 */
sealed class TestAction {
    object NavigationClipBoard : TestAction()
    object GoBack : TestAction()
}
