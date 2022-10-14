package com.preonboarding.customkeyboard.presentation

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.EditorInfo
import com.preonboarding.customkeyboard.databinding.ViewKeyboardBinding
import com.preonboarding.customkeyboard.presentation.clipboard.KeyboardClipboard
import com.preonboarding.customkeyboard.presentation.keyboard.KoreanKeyBoard

class KeyboardService : InputMethodService() {
    private lateinit var binding: ViewKeyboardBinding
    private lateinit var koreaKeyboard: KoreanKeyBoard
    private lateinit var keyboardClipboard: KeyboardClipboard
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
                    koreaKeyboard.inputConnection = currentInputConnection
                    binding.flKeyboard.addView(koreaKeyboard.getLayout())
                }
                Mode.ENGLISH -> {
                    binding.flKeyboard.removeAllViews()
                }
                Mode.SYMBOL -> {
                    binding.flKeyboard.removeAllViews()
                }
                Mode.CLIPBOARD -> {
                    binding.flKeyboard.removeAllViews()
                    keyboardClipboard.inputConnection = currentInputConnection
                    binding.flKeyboard.addView(keyboardClipboard.getLayout())
                }
            }
        }
    }

    override fun onCreateInputView(): View {
        koreaKeyboard = KoreanKeyBoard(applicationContext, layoutInflater, keyboardReplacer).apply {
            inputConnection = currentInputConnection
            init()
        }

        keyboardClipboard = KeyboardClipboard(applicationContext, layoutInflater, keyboardReplacer).apply {
            inputConnection = currentInputConnection
            init()
        }
        return binding.viewKeyboard
    }


    override fun updateInputViewShown() {
        super.updateInputViewShown()
        currentInputConnection.finishComposingText()
        if (currentInputEditorInfo.inputType == EditorInfo.TYPE_CLASS_NUMBER) {
            binding.flKeyboard.apply {
                removeAllViews()
            }
        } else {
            keyboardReplacer.changeMode(Mode.KOREA)
        }
    }
}
