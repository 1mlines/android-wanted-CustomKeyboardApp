package com.preonboarding.customkeyboard.presentation.clipboard

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import com.preonboarding.customkeyboard.domain.model.Bookmark
import com.preonboarding.customkeyboard.presentation.R
import com.preonboarding.customkeyboard.presentation.common.base.BaseActivity
import com.preonboarding.customkeyboard.presentation.common.extension.showSnackbar
import com.preonboarding.customkeyboard.presentation.databinding.ActivityClipboardBinding
import com.preonboarding.customkeyboard.presentation.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClipboardActivity : BaseActivity<ActivityClipboardBinding>() {
    override val layoutResourceId: Int = R.layout.activity_clipboard
    private lateinit var clipboardAdapter: ClipboardAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        initAdapter()
        setupDataBinding()
        initClipboard()
    }

    private fun setupDataBinding() {
        viewModel.localBookmarks.observe(this) {
            clipboardAdapter.submitList(it)
        }
    }

    private fun pasteBookmark(bookmark: Bookmark) {
        // focus 상태일때만
        binding.etClipboard.apply {
            if (isFocused) {
                text.append(bookmark.name)
            }
        }
    }

    private fun deleteBookmark(bookmark: Bookmark) {
        binding.root.showSnackbar(bookmark.toString())
        kotlin.runCatching {
            viewModel.deleteBookmark(bookmark = bookmark)
        }
            .onSuccess {
                binding.root.showSnackbar(
                    message = "${bookmark.name} 삭제 완료"
                )
            }
            .onFailure {
                binding.root.showSnackbar(
                    message = it.toString()
                )
            }
    }

    private fun initAdapter() {
        clipboardAdapter = ClipboardAdapter(onItemClick = { pasteBookmark(it) }, onDeleteClick = { deleteBookmark(it) } )
        binding.rvClipboard.adapter = clipboardAdapter
    }

    private fun initClipboard() {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        clipboard.addPrimaryClipChangedListener {
            val copiedText = clipboard.primaryClip?.getItemAt(0)?.text.toString().trim()

            kotlin.runCatching {
                viewModel.saveBookmark(Bookmark(name = copiedText))
            }
                .onSuccess {
                    binding.root.showSnackbar(
                        message = "$copiedText 저장 완료"
                    )
                }
                .onFailure {
                    binding.root.showSnackbar(
                        message = it.toString()
                    )
                }
        }
    }
}