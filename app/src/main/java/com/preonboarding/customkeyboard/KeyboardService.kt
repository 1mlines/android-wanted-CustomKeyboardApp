package com.preonboarding.customkeyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.EditorInfo
import com.preonboarding.customkeyboard.databinding.ViewKeyboardBinding

class KeyboardService : InputMethodService() {
    private lateinit var binding: ViewKeyboardBinding

    override fun onCreate() {
        super.onCreate()
        binding = ViewKeyboardBinding.inflate(layoutInflater)
    }

    private val keyboardReplacer = object : KeyboardActionListener {
        override fun changeMode(mode: Mode) {
            currentInputConnection?.finishComposingText()
            when (mode) {
                Mode.KOREA -> {
                    binding.flKeyboard.removeAllViews()
                }
                Mode.ENGLISH -> {
                    binding.flKeyboard.removeAllViews()
                }
                Mode.SYMBOL -> {
                    binding.flKeyboard.removeAllViews()
                }
            }
        }
    }

    override fun onCreateInputView(): View {
        return binding.viewKeyboard
    }


    override fun updateInputViewShown() {
        super.updateInputViewShown()
        currentInputConnection.finishComposingText()
        if (currentInputEditorInfo.inputType == EditorInfo.TYPE_CLASS_NUMBER) {
            binding.flKeyboard.apply {
                removeAllViews()
                // TODO 숫자 패드
            }
        } else {
            keyboardReplacer.changeMode(Mode.ENGLISH)
        }
    }
}
