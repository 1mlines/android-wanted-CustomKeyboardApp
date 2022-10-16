package com.preonboarding.customkeyboard.presentation.ui.input

import android.content.ClipboardManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.preonboarding.customkeyboard.domain.model.Bookmark
import com.preonboarding.customkeyboard.domain.model.ToolbarMenu
import com.preonboarding.customkeyboard.presentation.R
import com.preonboarding.customkeyboard.presentation.common.base.BaseActivity
import com.preonboarding.customkeyboard.presentation.common.extension.showSnackbar
import com.preonboarding.customkeyboard.presentation.databinding.ActivityInputBinding
import com.preonboarding.customkeyboard.presentation.ui.input.clipboard.ClipboardFragment
import com.preonboarding.customkeyboard.presentation.ui.input.keyboard.KeyboardFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputActivity : BaseActivity<ActivityInputBinding>() {
    override val layoutResourceId: Int = R.layout.activity_input
    private lateinit var clipboard: ClipboardManager
    private val viewModel: InputViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpDataBinding()
        initToolbar()
        initClipboard()
        observeData()
    }

    private fun setUpDataBinding() {
        viewModel.toolbarMenu.observe(this) {
            binding.layoutToolbar.toolbarMenu = it

            when(it) {
                ToolbarMenu.KEYBOARD -> {
                    replaceFragment(KeyboardFragment())
                }

                ToolbarMenu.CLIPBOARD -> {
                    replaceFragment(ClipboardFragment())
                }

                else -> {
                    replaceFragment(KeyboardFragment())
                }
            }
        }
    }

    private fun observeData() {
        // focus 상태일때만
        viewModel.curPasteBookmark.observe(this) {
            binding.etInput.apply {
                if (isFocused) {
                    text.append(it.name)
                }
            }
        }
    }

    private fun initToolbar() {
        binding.layoutToolbar.apply {
            layoutKeyboardTab.setOnClickListener {
                viewModel.changeMenu()
                changeFragment()
            }

            layoutClipboardTab.setOnClickListener {
                viewModel.changeMenu()
                changeFragment()
            }

        }
    }

    private fun changeFragment() {
        when(viewModel.toolbarMenu.value) {
            ToolbarMenu.KEYBOARD -> {
                replaceFragment(KeyboardFragment())
            }

            ToolbarMenu.CLIPBOARD -> {
                replaceFragment(ClipboardFragment())
            }

            else -> {
                replaceFragment(KeyboardFragment())
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_fragment_container, fragment)
            .commitAllowingStateLoss()
    }

    private fun initClipboard() {
        clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

        clipboard.addPrimaryClipChangedListener {
            val copiedText = clipboard.primaryClip?.getItemAt(0)?.text.toString().trim()

            kotlin.runCatching {
                viewModel.saveBookmark(Bookmark(name = copiedText))
            }
                .onSuccess {
                    binding.root.showSnackbar(
                        message = "저장 완료"
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