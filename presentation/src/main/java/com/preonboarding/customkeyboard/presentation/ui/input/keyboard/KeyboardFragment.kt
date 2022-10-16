package com.preonboarding.customkeyboard.presentation.ui.input.keyboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.preonboarding.customkeyboard.presentation.R
import com.preonboarding.customkeyboard.presentation.common.base.BaseFragment
import com.preonboarding.customkeyboard.presentation.databinding.FragmentKeyboardBinding
import com.preonboarding.customkeyboard.presentation.ui.input.InputViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KeyboardFragment : BaseFragment<FragmentKeyboardBinding>(R.layout.fragment_keyboard) {
    private val viewModel: InputViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}