package com.hugh.presentation.ui.keyboard

import android.view.LayoutInflater
import android.view.ViewTreeObserver
import android.view.inputmethod.InputConnection
import com.hugh.data.repository.ClipBoardRepository
import com.hugh.presentation.action.keyboardAction.KeyboardAction
import com.hugh.presentation.action.keyboardAction.KeyboardHandler
import com.hugh.presentation.action.keyboardAction.KeyboardState
import com.hugh.presentation.ui.keyboard.clipBoard.ClipBoard

class KeyboardController(
    private val layoutInflater: LayoutInflater,
    private val inputConnection: InputConnection,
    private val clipBoardRepository: ClipBoardRepository
) : KeyboardHandler {

    var rootHeight = 0
    var isFirst = true

    private val keyboardKorean: KeyboardKorean by lazy {
        KeyboardKorean(
            layoutInflater,
            inputConnection
        ).also { it.init() }
    }

    private val clipKeyboard: ClipBoard by lazy {
        ClipBoard(
            layoutInflater,
            inputConnection,
            clipBoardRepository,
            rootHeight
        ).also { it.init() }
    }

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
        block(
            KeyboardState.Keyboard(
                view = clipKeyboard.getLayout()
            )
        )
    }

    fun cancel() {
        clipKeyboard.cancel()
    }
}