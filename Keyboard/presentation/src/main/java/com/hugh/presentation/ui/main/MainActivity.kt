package com.hugh.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hugh.presentation.R
import com.hugh.presentation.action.ClipBoardActor
import com.hugh.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val keyboardViewModel: KeyboardViewModel by viewModels()
    private val clipBoardActor by lazy { ClipBoardActor(keyboardViewModel) }
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainBinding.actor = clipBoardActor

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                keyboardViewModel.copyFlow.collect { state ->
                    clipBoardActor.insertClipData(state)
                }
            }
        }
    }
}