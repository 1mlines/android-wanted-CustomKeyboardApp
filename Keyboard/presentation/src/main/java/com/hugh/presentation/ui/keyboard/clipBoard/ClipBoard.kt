package com.hugh.presentation.ui.keyboard.clipBoard

import android.app.ActionBar.LayoutParams
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.hugh.presentation.R
import com.hugh.presentation.action.clipAction.ClipBoardActor
import com.hugh.presentation.databinding.KeyboardClipboardBinding
import com.hugh.presentation.extension.dip
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by 서강휘
 */

class ClipBoard constructor(
    private val context: Context,
    private val layoutInflater: LayoutInflater,
    private val rootHeight: Int,
    private val clipController: ClipController,
    private val keyboardScope: CoroutineScope
) {
    private lateinit var clipboardBinding: KeyboardClipboardBinding
    private val clipBoardActor: ClipBoardActor by lazy {
        ClipBoardActor(clipController)
    }

    private val clipboardManager =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    fun init() {
        clipboardBinding =
            DataBindingUtil.inflate<KeyboardClipboardBinding?>(
                layoutInflater,
                R.layout.keyboard_clipboard,
                null,
                false
            ).apply {
                val layoutParams =
                    LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, rootHeight).apply {
                        marginStart = 10.dip()
                    }

                root.layoutParams = layoutParams
            }

        clipboardBinding.clipBoardRecyclerView.apply {
            adapter = ClipAdapter(clipBoardActor)
        }

        clipboardManager.addPrimaryClipChangedListener {
            clipboardManager.primaryClip?.let { clipData ->
                clipBoardActor.copy(
                    clipData.getItemAt(0).text.toString()
                )
            }
        }

        keyboardScope.launch {
            clipController.clipFlow.collect {
                clipboardBinding.emptyClipboardText.isVisible = it.isEmpty()

                (clipboardBinding.clipBoardRecyclerView.adapter as ClipAdapter).submitList(it)
            }
        }
    }

    fun getLayout(): View {
        return clipboardBinding.root
    }
}