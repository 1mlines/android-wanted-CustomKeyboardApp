package com.preonboarding.customkeyboard.presentation.ui.input.clipboard

import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.activityViewModels
import com.preonboarding.customkeyboard.domain.model.Bookmark
import com.preonboarding.customkeyboard.presentation.R
import com.preonboarding.customkeyboard.presentation.clipboard.ClipboardAdapter
import com.preonboarding.customkeyboard.presentation.common.base.BaseFragment
import com.preonboarding.customkeyboard.presentation.common.extension.showSnackbar
import com.preonboarding.customkeyboard.presentation.databinding.FragmentClipboardBinding
import com.preonboarding.customkeyboard.presentation.ui.input.InputViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClipboardFragment : BaseFragment<FragmentClipboardBinding>(R.layout.fragment_clipboard) {
    private lateinit var clipboardAdapter: ClipboardAdapter
    private val viewModel: InputViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDataBinding()
        initAdapter()
    }

    private fun setupDataBinding() {
        viewModel.localBookmarks.observe(viewLifecycleOwner) {
            clipboardAdapter.submitList(it)
        }
    }

    private fun pasteBookmark(bookmark: Bookmark) {
        viewModel.updatePasteBookmark(bookmark = bookmark)
    }

    private fun deleteBookmark(bookmark: Bookmark) {
        binding.root.showSnackbar(bookmark.toString())
        kotlin.runCatching {
            viewModel.deleteBookmark(bookmark = bookmark)
        }
            .onSuccess {
                binding.root.showSnackbar(
                    message = "삭제 완료"
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


}