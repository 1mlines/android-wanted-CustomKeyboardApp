package com.hugh.presentation.ui.keyboard.clipBoard

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
import kotlinx.coroutines.*

class ClipBoard constructor(
    private val layoutInflater: LayoutInflater,
    private val inputConnection: InputConnection?,
    private val clipBoardRepository: ClipBoardRepository,
    private val rootHeight: Int
) {
    private lateinit var clipboardBinding: KeyboardClipboardBinding

    private val job = Job() + CoroutineExceptionHandler { _, throwable ->

    }

    private val clipScope = CoroutineScope(job)

    fun init() {
        clipboardBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.keyboard_clipboard, null, false)

        val layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, rootHeight).apply {
            marginStart = 10.dip()
        }

        clipboardBinding.root.layoutParams = layoutParams

        clipboardBinding.clipBoardRecyclerView.apply {
            adapter = ClipAdapter()
        }

        clipScope.launch {
            clipBoardRepository.getClipsFlow().collect {
                (clipboardBinding.clipBoardRecyclerView.adapter as ClipAdapter).submitList(it)
            }
        }
    }

    fun cancel() {
        clipScope.cancel()
    }

    fun getLayout(): View {
        return clipboardBinding.root
    }

}