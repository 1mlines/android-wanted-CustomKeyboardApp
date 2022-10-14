package com.hugh.presentation.ui.keyboard

import android.app.ActionBar.LayoutParams
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputConnection
import androidx.databinding.DataBindingUtil
import com.hugh.data.repository.ClipBoardRepository
import com.hugh.presentation.R
import com.hugh.presentation.databinding.KeyboardClipboardBinding
import com.hugh.presentation.extension.dip

class KeyboardClipboard constructor(
    private val layoutInflater: LayoutInflater,
    private val inputConnection: InputConnection?,
    private val clipBoardRepository: ClipBoardRepository,
    private val rootHeight: Int
) {
    private lateinit var clipboardBinding: KeyboardClipboardBinding

    fun init() {
        clipboardBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.keyboard_clipboard, null, false)

        val layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, rootHeight).apply {
            marginStart = 10.dip()
        }

        clipboardBinding.root.layoutParams = layoutParams
    }

    fun getLayout(): View {
        return clipboardBinding.root
    }

}