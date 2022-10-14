package com.hugh.presentation.ui.keyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.ViewTreeObserver
import androidx.databinding.DataBindingUtil
import com.hugh.presentation.R
import com.hugh.presentation.databinding.KeyboardViewBinding

class KeyBoardService : InputMethodService(), ViewTreeObserver.OnGlobalLayoutListener {

    private lateinit var keyboardViewBinding: KeyboardViewBinding
    private lateinit var keyboardKorean: KeyboardKorean
    private lateinit var keyboardClipboard: KeyboardClipboard

    override fun onCreate() {
        super.onCreate()

        keyboardViewBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.keyboard_view, null, false)
    }

    override fun onCreateInputView(): View {
        keyboardKorean = KeyboardKorean(
            layoutInflater,
            currentInputConnection
        ).also { it.init() }

        keyboardViewBinding.keyboardFrame.addView(keyboardKorean.getLayout())
        keyboardViewBinding.keyboardFrame.viewTreeObserver.addOnGlobalLayoutListener(this)

        keyboardViewBinding.actionKeyHome.keyButton.apply {
            text = "Home"

            setOnClickListener {
                keyboardViewBinding.keyboardFrame.removeAllViews()
                keyboardViewBinding.keyboardFrame.addView(keyboardKorean.getLayout())
            }
        }

        keyboardViewBinding.actionKeyClipboard.keyButton.apply {
            text = "Clip"

            setOnClickListener {
                keyboardViewBinding.keyboardFrame.removeAllViews()
                keyboardViewBinding.keyboardFrame.addView(keyboardClipboard.getLayout())
            }
        }

        return keyboardViewBinding.root
    }

    override fun onGlobalLayout() {
        keyboardClipboard =
            KeyboardClipboard(
                layoutInflater,
                currentInputConnection,
                keyboardViewBinding.keyboardFrame.measuredHeight
            ).also { it.init() }

        keyboardViewBinding.keyboardFrame.viewTreeObserver.removeOnGlobalLayoutListener(this)
    }
}