package com.hugh.presentation.ui.keyboard

import android.view.LayoutInflater
import android.view.ViewTreeObserver
import android.view.inputmethod.InputConnection
import com.hugh.data.repository.ClipBoardRepository
import com.hugh.presentation.action.keyboardAction.KeyboardAction
import com.hugh.presentation.action.keyboardAction.KeyboardHandler
import com.hugh.presentation.action.keyboardAction.KeyboardState

class KeyboardController(
    private val layoutInflater: LayoutInflater,
    private val inputConnection: InputConnection,
    private val clipBoardRepository: ClipBoardRepository
) : KeyboardHandler {

    var rootHeight = 0
    var isFirst = true

    override fun keyboardAction(action: KeyboardAction) {
        when (action) {
            is KeyboardAction.NavigateClipKeyboard -> {
                createClipKeyboard(action.block)
            }
            is KeyboardAction.NavigateNumberKeyboard -> {
                createNumberKeyboard(action.block)
            }
        }
    }

    private fun createNumberKeyboard(block: (KeyboardState) -> Unit) {
        val keyboardKorean = KeyboardKorean(
            layoutInflater,
            inputConnection
        ).also { it.init() }

        if (isFirst) {
            keyboardKorean.getLayout().viewTreeObserver.addOnGlobalLayoutListener(object :
                ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    rootHeight = keyboardKorean.getLayout().measuredHeight
                    keyboardKorean.getLayout().viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            })

            isFirst = false
        }

        block(
            KeyboardState.Keyboard(
                view = keyboardKorean.getLayout()
            )
        )
    }

    private fun createClipKeyboard(block: (KeyboardState) -> Unit) {
        val clipKeyboard = KeyboardClipboard(
            layoutInflater,
            inputConnection,
            clipBoardRepository,
            rootHeight
        ).also { it.init() }

        block(
            KeyboardState.Keyboard(
                view = clipKeyboard.getLayout()
            )
        )
    }
}