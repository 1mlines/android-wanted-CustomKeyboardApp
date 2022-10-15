package com.hugh.presentation.action.compose.info

/**
 * @Created by 김현국 2022/10/14
 */
class InfoActionActor(private val infoActionHandler: InfoActionHandler) {
    fun navigationKeyBoardScreen(action: InfoAction) {
        infoActionHandler.navigateAction(action)
    }
}
