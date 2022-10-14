package com.hugh.presentation.ui.keyboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewTreeObserver
import android.view.inputmethod.InputConnection
import com.hugh.data.repository.ClipBoardRepository
import com.hugh.presentation.action.keyboardAction.KeyboardAction
import com.hugh.presentation.action.keyboardAction.KeyboardHandler
import com.hugh.presentation.action.keyboardAction.KeyboardState
import com.hugh.presentation.ui.keyboard.clipBoard.ClipBoard
import com.hugh.presentation.ui.keyboard.clipBoard.ClipController
import kotlinx.coroutines.CoroutineScope

/**
 * Created by 서강휘
 */

class KeyboardController(
    private val context: Context,
    private val layoutInflater: LayoutInflater,
    private val inputConnection: InputConnection,
    private val clipBoardRepository: ClipBoardRepository,
    private val hangulUtil: HangulUtil,
    private val keyboardScope: CoroutineScope,
) : KeyboardHandler {

    var rootHeight = 0
    var isFirst = true

    private val keyboardKorean: KeyboardKorean by lazy {
        KeyboardKorean(
            layoutInflater,
            inputConnection,
            hangulUtil
        ).also { it.init() }
    }

    private val clipKeyboard: ClipBoard by lazy {
        ClipBoard(
            context = context,
            layoutInflater = layoutInflater,
            rootHeight = rootHeight,
            clipController = ClipController(
                clipBoardRepository = clipBoardRepository,
                inputConnection = inputConnection,
                hangulUtil = hangulUtil,
                keyboardScope = keyboardScope
            ),
            keyboardScope = keyboardScope
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
}