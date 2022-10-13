package com.preonboarding.customkeyboard.keyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.myhome.rpgkeyboard.KeyboardInterationListener
import com.preonboarding.customkeyboard.R

class KeyboardService : InputMethodService() {

    lateinit var keyboardView: LinearLayout
    lateinit var keyboardFrame: FrameLayout

    lateinit var keyboardKorean: KeyboardKorean

    val keyboardInterationListener = object : KeyboardInterationListener {
        //inputconnection이 null일경우 재요청하는 부분 필요함
        override fun modechange(mode: Int) {
            currentInputConnection.finishComposingText()
            keyboardFrame.removeAllViews()
            keyboardKorean.inputConnection = currentInputConnection
            keyboardFrame.addView(keyboardKorean.getLayout())
        }
    }

    override fun onCreateInputView(): View {
        keyboardView = layoutInflater.inflate(R.layout.view_keyboard, null) as LinearLayout
        keyboardFrame = keyboardView.findViewById(R.id.framelayout_keyboard)

        keyboardKorean =
            KeyboardKorean(applicationContext, layoutInflater, keyboardInterationListener)
        keyboardKorean.inputConnection = currentInputConnection
        keyboardKorean.init()
        return keyboardView
    }

    override fun updateInputViewShown() {
        super.updateInputViewShown()
        currentInputConnection.finishComposingText()
        keyboardInterationListener.modechange(1)
    }
}