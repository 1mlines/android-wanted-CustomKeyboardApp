package com.preonboarding.customkeyboard.keyboard.hyunsu

import android.content.ClipboardManager
import android.content.Context
import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.EditorInfo
import com.preonboarding.customkeyboard.data.local.entity.ClipboardEntity
import com.preonboarding.customkeyboard.databinding.ViewKeyboardBinding
import com.preonboarding.customkeyboard.domain.model.Clipboard
import com.preonboarding.customkeyboard.domain.usecase.RoomUseCase
import com.preonboarding.customkeyboard.presentation.clipboard.ClipboardActionListener
import com.preonboarding.customkeyboard.presentation.clipboard.KeyboardClipboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@AndroidEntryPoint
class KeyboardService : InputMethodService(), ClipboardManager.OnPrimaryClipChangedListener {
    @Inject
    lateinit var roomUseCase: RoomUseCase

    private val clipList = MutableStateFlow<List<Clipboard>>(emptyList())
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
        serviceScope.launch {
            roomUseCase.getAllClipData().collect { list ->
                clipList.update {
                    list.toList()
                }
            }
        }
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
        override fun deleteClipData(clipboard: ClipboardEntity) {
            serviceScope.launch {
                roomUseCase.deleteClipData(clipboard)
                roomUseCase.getAllClipData().collect { list ->
                    clipList.update {
                        list.toList()
                    }
                }
            }
        }

        override fun copyClipData(clipData: String) {
            serviceScope.launch {
                val pasteText = roomUseCase.getClipData(clipData).clipData
                currentInputConnection.commitText(pasteText, 1)
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
                clipboardListener,
            ).apply {
                inputConnection = currentInputConnection
                init()
            }

        serviceScope.launch {
            clipList.collect {
                keyboardClipboard.updateClipList(it)
            }
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
                runCatching {
                    roomUseCase.insertClipData(clipData.getItemAt(0).text.toString())
                }.also {
                    roomUseCase.getAllClipData().collect { list ->
                        clipList.update {
                            list.toList()
                        }
                    }
                }
            }
        }
    }
}
