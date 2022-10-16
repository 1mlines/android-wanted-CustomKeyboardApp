package com.preonboarding.customkeyboard.presentation.keyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.preonboarding.customkeyboard.presentation.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KeyBoardService : InputMethodService() {
    private lateinit var keyboardView: LinearLayout
    private lateinit var keyboardFrame: FrameLayout
    private lateinit var qwertyKeyboard: QwertyKeyboard


    override fun onCreate() {
        super.onCreate()
        keyboardView = layoutInflater.inflate(R.layout.keyboard_view, null) as LinearLayout
        keyboardFrame = keyboardView.findViewById(R.id.keyboard_frame)
    }

    override fun onCreateInputView(): View {
        qwertyKeyboard = QwertyKeyboard(applicationContext, layoutInflater).apply {
            inputConnection = currentInputConnection
            init()
        }

        return keyboardView
    }

    override fun updateInputViewShown() {
        super.updateInputViewShown()
        currentInputConnection.finishComposingText()
        keyboardFrame.removeAllViews()
        qwertyKeyboard.inputConnection = currentInputConnection
        keyboardFrame.addView(qwertyKeyboard.getLayout())
    }
}