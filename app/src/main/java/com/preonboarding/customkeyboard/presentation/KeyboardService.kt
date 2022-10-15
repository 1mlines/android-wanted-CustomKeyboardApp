package com.preonboarding.customkeyboard.presentation

import android.content.ClipboardManager
import android.content.Context
import android.inputmethodservice.InputMethodService
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import com.preonboarding.customkeyboard.databinding.ViewKeyboardBinding
import com.preonboarding.customkeyboard.domain.usecase.RoomUseCase
import com.preonboarding.customkeyboard.presentation.clipboard.ClipboardActionListener
import com.preonboarding.customkeyboard.presentation.clipboard.KeyboardClipboard
import com.preonboarding.customkeyboard.presentation.keyboard.KoreanKeyBoard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class KeyboardService : InputMethodService(), ClipboardManager.OnPrimaryClipChangedListener {
    @Inject
    lateinit var roomUseCase: RoomUseCase

    private lateinit var clipboardManager: ClipboardManager

    private lateinit var binding: ViewKeyboardBinding
    private lateinit var koreaKeyboard: KoreanKeyBoard
    private lateinit var keyboardClipboard: KeyboardClipboard

    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    override fun onCreate() {
        super.onCreate()
        binding = ViewKeyboardBinding.inflate(layoutInflater)
        clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboardManager.addPrimaryClipChangedListener(this)
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

    private val clipboardListener = object : ClipboardActionListener {
        override fun deleteClipData(id: Int) {
            serviceScope.launch {
                roomUseCase.deleteClipData(id)
            }
        }

        override fun copyClipData(text: String) {
            serviceScope.launch {
                roomUseCase.insertClipData(text)
            }
        }

        override fun getClipData() {
            serviceScope.launch {
                roomUseCase.getAllClipData()
            }
        }
    }

    override fun onCreateInputView(): View {
        koreaKeyboard = KoreanKeyBoard(applicationContext, layoutInflater, keyboardReplacer).apply {
            inputConnection = currentInputConnection
            init()
        }

        keyboardClipboard =
            KeyboardClipboard(
                applicationContext,
                layoutInflater,
                keyboardReplacer,
                clipboardListener
            ).apply {
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

    override fun onDestroy() {
        super.onDestroy()

        serviceScope.cancel()
    }

    override fun onPrimaryClipChanged() {
        serviceScope.launch {
            val clipData = clipboardManager.primaryClip
            clipData?.let {
                roomUseCase.insertClipData(it.getItemAt(0).text.toString())
                Log.d("클립보드", "onPrimaryClipChanged: ${it.getItemAt(0).text}")
            }
        }
    }
}
