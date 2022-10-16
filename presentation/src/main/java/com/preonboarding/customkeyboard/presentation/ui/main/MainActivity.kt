package com.preonboarding.customkeyboard.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.preonboarding.customkeyboard.presentation.R
import com.preonboarding.customkeyboard.presentation.common.base.BaseActivity
import com.preonboarding.customkeyboard.presentation.databinding.ActivityMainBinding
import com.preonboarding.customkeyboard.presentation.ui.input.InputActivity
import com.preonboarding.customkeyboard.presentation.ui.pay.PayDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResourceId: Int = R.layout.activity_main
    private val viewModel: MainViewModel by viewModels()

    private val payDialog: PayDialog by lazy {
        PayDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        initView()
        initAdapter()
    }

    private fun initView() {
        binding.tvPay.setOnClickListener {
            payDialog.show()
        }

        binding.ivKeyboard.setOnClickListener {
            startActivity(Intent(this, InputActivity::class.java))
        }
    }

    private fun initAdapter() {
        val keywordAdapter = KeywordAdapter()
        val reviewAdapter = ReviewAdapter()

        binding.rcvKeyword.apply {
            this.adapter = keywordAdapter
            this.hasFixedSize()
        }

        binding.rcvReview.apply {
            this.adapter = reviewAdapter
            this.hasFixedSize()
        }
    }
}
