package com.hugh.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.hugh.presentation.action.clipAction.ClipBoardActor
import com.hugh.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val keyboardViewModel: KeyboardViewModel by viewModels()
    private val clipBoardActor by lazy { ClipBoardActor(keyboardViewModel) }
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomKeyBoardApp()
        }
//        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        mainBinding.actor = clipBoardActor
//
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                keyboardViewModel.copyFlow.collect { state ->
//                    keyboardViewModel.insertClipData(state)
//                }
//            }
//        }
    }
}
