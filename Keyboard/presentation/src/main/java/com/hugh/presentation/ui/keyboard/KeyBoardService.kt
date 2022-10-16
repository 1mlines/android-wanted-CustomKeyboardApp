package com.hugh.presentation.ui.keyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import com.hugh.data.repository.ClipBoardRepository
import com.hugh.presentation.R
import com.hugh.presentation.action.keyboardAction.KeyboardAction
import com.hugh.presentation.action.keyboardAction.KeyboardState
import com.hugh.presentation.databinding.KeyboardViewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class KeyBoardService : InputMethodService() {

    @Inject
    lateinit var clipBoardRepository: ClipBoardRepository

    private lateinit var keyboardViewBinding: KeyboardViewBinding
    private lateinit var keyboardController: KeyboardController

    private lateinit var keyboardCoroutineContext: CoroutineContext
    private lateinit var keyboardScope: CoroutineScope

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

    override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
        super.onStartInputView(info, restarting)

        keyboardCoroutineContext =
            Dispatchers.Main.immediate + SupervisorJob() + CoroutineExceptionHandler { _, throwable -> }
        keyboardScope = CoroutineScope(keyboardCoroutineContext)

        keyboardController =
            KeyboardController(
                context = applicationContext,
                layoutInflater = layoutInflater,
                inputConnection = currentInputConnection,
                clipBoardRepository = clipBoardRepository,
                hangulUtil = HangulUtil(),
                keyboardScope = keyboardScope
            )

        keyboardController.keyboardAction(
            KeyboardAction.NavigateNumberKeyboard(navigationBlock())
        )
    }

    override fun onFinishInputView(finishingInput: Boolean) {
        super.onFinishInputView(finishingInput)
        keyboardScope.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        keyboardScope.cancel()
    }
}