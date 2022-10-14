package com.hugh.presentation.ui.keyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import androidx.databinding.DataBindingUtil
import com.hugh.data.repository.ClipBoardRepository
import com.hugh.presentation.R
import com.hugh.presentation.action.keyboardAction.KeyboardAction
import com.hugh.presentation.action.keyboardAction.KeyboardState
import com.hugh.presentation.databinding.KeyboardViewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class KeyBoardService : InputMethodService() {

    @Inject
    lateinit var clipBoardRepository: ClipBoardRepository

    private lateinit var keyboardViewBinding: KeyboardViewBinding
    private lateinit var keyboardController: KeyboardController

    private fun navigationBlock(): (KeyboardState) -> Unit = { state ->
        when (state) {
            is KeyboardState.Keyboard -> {
                keyboardViewBinding.keyboardFrame.removeAllViews()
                keyboardViewBinding.keyboardFrame.addView(state.view)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        keyboardViewBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.keyboard_view, null, false)
    }

    override fun onCreateInputView(): View {
        keyboardController =
            KeyboardController(layoutInflater, currentInputConnection, clipBoardRepository)

        keyboardController.keyboardAction(
            KeyboardAction.NavigateNumberKeyboard(navigationBlock())
        )

        keyboardViewBinding.actionKeyHome.keyButton.apply {
            text = "Home"

            setOnClickListener {
                keyboardController.keyboardAction(
                    KeyboardAction.NavigateNumberKeyboard(navigationBlock())
                )
            }
        }

        keyboardViewBinding.actionKeyClipboard.keyButton.apply {
            text = "Clip"

            setOnClickListener {
                keyboardController.keyboardAction(
                    KeyboardAction.NavigateClipKeyboard(navigationBlock())
                )
            }
        }

        return keyboardViewBinding.root
    }
}