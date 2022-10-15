package com.hugh.presentation.ui.keyboard

import android.util.Log
import com.hugh.presentation.action.keyboard.KeyBoardAction
import com.hugh.presentation.action.keyboard.KeyBoardHandler

class Keyboard: KeyBoardHandler {
    override fun keyboardAction(action: KeyBoardAction) {
        when (action) {
            is KeyBoardAction.CAPS -> {
                Log.d("테스트", "keyboardAction: 테스트 ")
            }
            is KeyBoardAction.Delete -> {
                Log.d("테스트", "keyboardAction: 테스트 ")

            }
            is KeyBoardAction.Space -> {
                Log.d("테스트", "keyboardAction: 테스트 ")

            }
        }
    }
}