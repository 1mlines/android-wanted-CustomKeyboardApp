package com.hugh.presentation.action.compose.test

/**
 * @Created by 김현국 2022/10/14
 */
class TestActionActor(private val testActionHandler: TestActionHandler) {

    fun navigationClipBoardScreen(action: TestAction) {
        testActionHandler.navigateAction(action)
    }
}
